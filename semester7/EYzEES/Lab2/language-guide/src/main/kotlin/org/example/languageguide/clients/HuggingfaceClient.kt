package org.example.languageguide.clients

import org.example.languageguide.config.FeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader


@FeignClient(name = "hugging-face", url = "https://api-inference.huggingface.co")
interface HuggingfaceClient {
    @PostMapping("/models/papluca/xlm-roberta-base-language-detection")
    fun detectLanguage(@RequestBody requestBody: Map<String, Any>,
                       @RequestHeader("Authorization") authorization: String): String
}