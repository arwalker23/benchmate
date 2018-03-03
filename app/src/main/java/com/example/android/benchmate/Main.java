package com.example.android.benchmate;

public class Main {

    public static void main(String[] args) {
        Reagent water = new Reagent("Water");
        Reagent PCRBuffer = new Reagent("PCR Buffer");
        Reagent templateDNA = new Reagent("Template DNA");
        Reagent primers = new Reagent("Primers");
        Reagent dNTPs = new Reagent("dNTPs");
        Experiment basicPCR = new Experiment("001", "PCR");

        basicPCR.addReagent(water, UnitOfMeasure.MICROLITRE);
        basicPCR.addReagent(PCRBuffer, UnitOfMeasure.MICROLITRE);
        basicPCR.addReagent(templateDNA, UnitOfMeasure.MICROLITRE);
        basicPCR.addReagent(primers, UnitOfMeasure.MICROLITRE);
        basicPCR.addReagent(dNTPs, UnitOfMeasure.MICROLITRE);

        basicPCR.printExperiment();

//        String[] wellNames = {"A", "B", "C", "D", "E", "F", "G", "H"};
//        for(String well : wellNames) {
//            for(int i = 1; i < 13; i++) {
//                System.out.print(well + i + ", ");
//            }
//        }
    }
}
