package com.benchmate.domain;

import java.io.Serializable;

public class Reagent implements Serializable {
    private String name;
    private double amount;
    private String unitOfMeasure;

    public Reagent(String name, double amount, String unitOfMeasure) {
        this.name = name;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reagent reagent = (Reagent) o;

        return name != null ? name.equals(reagent.name) : reagent.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    // TODO: implement loading reagents from CSV file
//    public static Reagent createFromCsvLine(String line) {
//        Reagent reagent = new Reagent();
//        String[] tokens = line.split(",", -1);//add "-1" to split method, since the comma is matched, but the empty match is not in the result without it
//
//        reagent.setName(tokens[0]);
//        try {
//            reagent.setUnitOfMeasure(tokens[1]);
//        } catch (IllegalArgumentException ex) {
//            //todo: enhance me for each field, with some error message and put null?
//            reagent.setUnitOfMeasure(null);
//        }
//        //todo: implement me, parsing csv
//        return reagent;
//    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Reagent{");
        sb.append(", name='").append(name).append('\'');
        sb.append(", unitOfMeasure=").append(unitOfMeasure);
        sb.append('}');
        return sb.toString();
    }

    public String prettyPrint() {
        return this.getAmount() + " " + this.getUnitOfMeasure() + " " + this.getName();
    }
}
