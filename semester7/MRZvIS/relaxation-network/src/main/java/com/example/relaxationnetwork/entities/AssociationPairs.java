package com.example.relaxationnetwork.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationPairs {
    private List<String> russian;
    private List<String> english;
}
