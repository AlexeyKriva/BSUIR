package org.example.languageguide.entities

class DocStatistic {
    var htmlDoc: HtmlDoc = HtmlDoc()
    var statistic: MutableMap<String, Int> = mutableMapOf()
    var language: String = ""

    constructor(htmlDoc: HtmlDoc, statistic: MutableMap<String, Int>, language: String) {
        this.htmlDoc = htmlDoc
        this.statistic = statistic
        this.language = language
    }

    constructor()

    override fun toString(): String {
        return "DocStatistic(htmlDoc=$htmlDoc, statistic=$statistic, language='$language')"
    }


}