package com.software.bsuir.abstractbuilder.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbstractResponse {
    private String source;
    private GeneralAbstract generalAbstract;
}
