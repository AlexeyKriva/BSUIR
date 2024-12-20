package com.example.relaxationnetwork.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestAssociationTriple {
    private List<List<String>> pictures;
    private List<String> numbers;
    private List<String> forward;
}
