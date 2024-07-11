package com.laborotory.work.lab1.models;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Pairs {
    @Valid
    private List<Pair> pairsOfNumbers;

    public List<Pair> checkSystem(){
        int id = 0;
        for (Pair expression: this.pairsOfNumbers){
            this.pairsOfNumbers.set(id++, expression.checkSystem());
        }
        return this.pairsOfNumbers;
    }

    public static List<Pair> createListOfPairsOfNumbers(int amountOfNumbers) {
        List<Pair> pairsOfNumbers = new ArrayList<>();
        for (int i = 0; i < amountOfNumbers; i++) {
            pairsOfNumbers.add(new Pair());
        }

        return pairsOfNumbers;
    }
}
