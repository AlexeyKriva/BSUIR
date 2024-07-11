package com.laborotory.work.lab1.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class InformationHistory {
    private Pair pair;
    private List<String> partialSums;
    private List<String> partialProducts;

    public static List<InformationHistory> createHistory(List<InformationHistory> informationGraphs, List<Conveyor> conveyor, int startIndex) {
        for (Conveyor value : conveyor) {
            List<String> newPartialSums = new ArrayList<>(informationGraphs.get(startIndex).getPartialSums());
            newPartialSums.add(value.getPartialSum());
            List<String> newPartialProduct = new ArrayList<>(informationGraphs.get(startIndex).getPartialProducts());
            newPartialProduct.add(value.getPartialProduct());
            informationGraphs.set(startIndex, new InformationHistory(informationGraphs.get(startIndex).getPair(),
                    newPartialSums, newPartialProduct));
            startIndex++;
        }
        return informationGraphs;
    }


}
