package com.software.bsuir.abstractbuilder.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralAbstract {
    @JsonProperty("summary_text")
    private String summaryText;
}
