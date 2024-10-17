package org.example.languageguide.controllers

import org.example.languageguide.entities.HtmlDoc
import org.example.languageguide.entities.DocStatistic
import org.example.languageguide.services.HtmlDocService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(value = ["/docs"])
class HtmlDocRestController(private var htmlDocService: HtmlDocService) {
    @GetMapping
    fun getAllHtmlDocuments(): ResponseEntity<MutableList<HtmlDoc>> {
        return ResponseEntity(htmlDocService.findAllHtmlDocs(), HttpStatus.OK)
    }

    @PostMapping
    fun saveHtmlDocs(@RequestParam("htmlFiles") htmlFiles: MutableList<MultipartFile>): ResponseEntity<MutableList<HtmlDoc>> {
        return ResponseEntity(htmlDocService.saveAllHtmlDocs(htmlFiles), HttpStatus.CREATED)
    }

    @DeleteMapping("{id}")
    fun deleteHtmlDoc(@PathVariable("id") id: Long): ResponseEntity<String> {
        htmlDocService.deleteHtmlDoc(id)
        val response: String = "Html document with id $id was successfully deleted"
        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping
    fun deleteAllHtmlDocs(): ResponseEntity<String> {
        htmlDocService.deleteAllHtmlDoc()
        val response: String = "All html documents was successfully deleted"
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/lang")
    fun defineLangsFromHtmlDocsNGramm(@RequestParam("save") save: Boolean,
                                @RequestParam("htmlFiles") htmlFiles: MutableList<MultipartFile>,
                                      @RequestParam("type") type: String):
            ResponseEntity<MutableList<DocStatistic>> {
        return ResponseEntity(htmlDocService.defineLangsFromHtmlDocs(save, htmlFiles, type), HttpStatus.OK)
    }
}