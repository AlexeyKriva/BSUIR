package com.software.bsuir.abstractbuilder.clients;

import com.software.bsuir.abstractbuilder.entities.GeneralAbstract;
import com.software.bsuir.abstractbuilder.entities.HuggingFaceRequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

@FeignClient(name = "huggingface")
public interface HuggingfaceClient {
    // /facebook/bart-large-cnn
    @PostMapping("/sshleifer/distilbart-cnn-12-6")
    ResponseEntity<List<GeneralAbstract>> buildAbstractsWithModel(
            @RequestHeader("Authorization") String token,
            @RequestBody HuggingFaceRequestBody huggingFaceRequestBody
    );
}