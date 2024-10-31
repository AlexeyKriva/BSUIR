package org.example.languageguide.services

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.apache.tika.language.LanguageIdentifier
import org.example.languageguide.clients.HuggingfaceClient
import org.example.languageguide.entities.DocStatistic
import org.example.languageguide.entities.HtmlDoc
import org.example.languageguide.entities.LanguageScore
import org.example.languageguide.entities.SimpleLanguage
import org.example.languageguide.repositories.HtmlDocRep
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.time.LocalDateTime


@Service
class HtmlDocService(private val htmlDocRep: HtmlDocRep, val huggingfaceClient: HuggingfaceClient) {
    val N_GRAMM = "n-gramm"
    val NEURAL_NETWORK = "neural_network"
    val ALPHABET = "alphabet"

    @Value("\${huggingface.api.key}")
    var HUGGING_FACE_API_KEY = "";

    //val objectMapper = ObjectMapper().registerModule(KotlinModule())

    companion object {
        var indexOfFile: Int = 1
    }

    private val alphabetFrequencies: Map<SimpleLanguage, Map<String, Double>> = mapOf(
        SimpleLanguage.ENGLISH to mapOf(
            "a" to 0.0813,
            "b" to 0.0093,
            "c" to 0.0315,
            "d" to 0.0355,
            "e" to 0.1510,
            "f" to 0.0096,
            "g" to 0.0097,
            "h" to 0.0108,
            "i" to 0.0694,
            "j" to 0.0071,
            "k" to 0.0016,
            "l" to 0.0568,
            "m" to 0.0323,
            "n" to 0.0642,
            "o" to 0.0527,
            "p" to 0.0303,
            "q" to 0.0089,
            "r" to 0.0643,
            "s" to 0.0791,
            "t" to 0.0711,
            "u" to 0.0605,
            "v" to 0.0183,
            "w" to 0.0004,
            "x" to 0.0042,
            "y" to 0.0019,
            "z" to 0.0021
        ),
        SimpleLanguage.FRENCH to mapOf(
            "a" to 0.0813,
            "b" to 0.0093,
            "c" to 0.0315,
            "d" to 0.0355,
            "e" to 0.1510,
            "é" to 0.0312,
            "è" to 0.0131,
            "ê" to 0.0050,
            "ë" to 0.0010,
            "f" to 0.0096,
            "g" to 0.0097,
            "h" to 0.0108,
            "i" to 0.0694,
            "j" to 0.0071,
            "k" to 0.0016,
            "l" to 0.0568,
            "m" to 0.0323,
            "n" to 0.0642,
            "o" to 0.0527,
            "ô" to 0.0032,
            "p" to 0.0303,
            "q" to 0.0089,
            "r" to 0.0643,
            "s" to 0.0791,
            "t" to 0.0711,
            "u" to 0.0605,
            "û" to 0.0020,
            "v" to 0.0183,
            "w" to 0.0004,
            "x" to 0.0042,
            "y" to 0.0019,
            "z" to 0.0021,
            "î" to 0.0045,
            "ç" to 0.0030,
            "à" to 0.0025
        )
    )


    fun findAllHtmlDocs(): MutableList<HtmlDoc> {
        return htmlDocRep.findAll()
    }

    fun saveAllHtmlDocs(htmlFiles: List<MultipartFile>): MutableList<HtmlDoc> {
        var convertedHtmlDocs: MutableList<HtmlDoc> = mutableListOf()

        for (htmlFile in htmlFiles) {
            if (htmlFile.isEmpty) {
                continue
            }

            convertedHtmlDocs.add(convertHtmlFileToHtmlDoc(htmlFile))
        }
        return htmlDocRep.saveAll(convertedHtmlDocs)
    }

    private fun convertHtmlFileToHtmlDoc(htmlFile: MultipartFile): HtmlDoc {
        val htmlDocName = htmlFile.originalFilename ?: "unknown"
        val htmlDocWeight = htmlFile.size
        val htmlFileContent = htmlFile.inputStream.bufferedReader().use { it.readText() }

        val document = Jsoup.parse(htmlFileContent)
        val htmlTextContent = document.body().text()

        return HtmlDoc(htmlDocName, htmlTextContent, LocalDateTime.now(), htmlDocWeight)
    }

    fun deleteHtmlDoc(id: Long): Unit {
        htmlDocRep.deleteById(id)
    }

    fun deleteAllHtmlDoc(): Unit {
        htmlDocRep.deleteAll()
    }

    fun defineLangsFromHtmlDocs(saveOn: String?, htmlFiles: MutableList<MultipartFile>, type: String):
            MutableList<DocStatistic> {
        var htmlDocs: MutableList<HtmlDoc> = mutableListOf()

        val save = saveOn != null

        for (htmlFile in htmlFiles) {
            htmlDocs.add(convertHtmlFileToHtmlDoc(htmlFile))
        }

        var docsStatistics: MutableList<DocStatistic> = mutableListOf()
        for (htmlDoc in htmlDocs) {
            var lang: String = "";

            if (type.lowercase() == N_GRAMM) {
                lang = checkLanguage(LanguageIdentifier(htmlDoc.content).language)
            } else if (type.lowercase() == NEURAL_NETWORK) {
                val content = htmlDoc.content.lowercase()
                val requestBody = mapOf("inputs" to content)
                val authorizationHeader = "Bearer $HUGGING_FACE_API_KEY"

                val response: String = huggingfaceClient.detectLanguage(requestBody, authorizationHeader);

                val builder = GsonBuilder()
                val gson = builder.create()
                val result: List<List<LanguageScore>> =
                    gson.fromJson(response, object : TypeToken<List<List<LanguageScore>>>() {}.type)

                lang = checkLanguage(result[0][0].label)
            } else if (type.lowercase() == ALPHABET) {
                lang = alphabetMethod(htmlDoc.content.lowercase())
            } else {
                lang = checkLanguage("")
            }

            val docStatistic = DocStatistic(htmlDoc, analyzeTextStatistic(htmlDoc.content), lang)
            if (save) {
                saveDocStatistic(docStatistic)
            }
            docsStatistics.add(docStatistic)
        }

        return docsStatistics;
    }

    fun <T> getListType(classType: Class<T>): ParameterizedType {
        return object : ParameterizedType {
            override fun getActualTypeArguments(): Array<Type> = arrayOf(classType)
            override fun getRawType(): Type = List::class.java
            override fun getOwnerType(): Type? = null
        }
    }

    fun saveDocStatistic(docStatistic: DocStatistic): Unit {
        val file = File(
            "/Users/aliaksei/Desktop/BSUIR/semester7/EYzEES/Lab2/language-guide/files/" +
                    "docStatistic${indexOfFile++}.txt"
        );

        if (!file.exists()) {
            file.createNewFile()
        }
        file.writeText(docStatistic.toString())
    }

    fun alphabetMethod(content: String): String {
        val textLength = content.length

        val letterCounts = mutableMapOf<String, Int>()
        for (letter in content) {
            if (letter.isLetter()) {
                letterCounts[letter.toString()] = letterCounts.getOrDefault(letter.toString(), 0) + 1
                if (!alphabetFrequencies.getValue(SimpleLanguage.ENGLISH).containsKey(letter.toString()) &&
                    alphabetFrequencies.getValue(SimpleLanguage.FRENCH).containsKey(letter.toString())
                ) {
                    return SimpleLanguage.FRENCH.name
                }
            }
        }

        val frequencies = mutableMapOf<String, Double>()
        for ((letter, count) in letterCounts) {
            frequencies[letter] = count.toDouble() / textLength
        }

        val scores = mutableMapOf(SimpleLanguage.ENGLISH to 0.0, SimpleLanguage.FRENCH to 0.0)

        for ((lang, freqDict) in alphabetFrequencies) {
            for ((letter, expectedFreq) in freqDict) {
                scores[lang] = scores[lang]!! + Math.pow(frequencies.getOrDefault(letter, 0.0) - expectedFreq, 2.0)
            }
        }

        val predictedLanguage = scores.minByOrNull { it.value }?.key ?: SimpleLanguage.ENGLISH

        return predictedLanguage.name
    }

    private fun analyzeTextStatistic(content: String): MutableMap<String, Int> {
        val wordCount = content.split("\\s+".toRegex()).size
        val characterCount = content.length
        val sentenceCount = content.split(".", "!", "?").size - 1

        return mutableMapOf(
            "words" to wordCount,
            "characters" to characterCount,
            "sentences" to sentenceCount
        )
    }

    private fun checkLanguage(lang: String): String {
        when (lang) {
            "en" -> return "ENGLISH"
            "fr" -> return "FRENCH"
            else -> return "This language isn't English or France"
        }

    }
}