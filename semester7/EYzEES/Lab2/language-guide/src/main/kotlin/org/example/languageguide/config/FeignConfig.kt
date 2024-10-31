package org.example.languageguide.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class FeignConfig {
    @Value("\${huggingface.api.key}")
    private val apiKey: String? = null

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            requestTemplate.header("Authorization", "Bearer $apiKey")
            requestTemplate.header("Content-Type", "application/json")
        }
    }
}