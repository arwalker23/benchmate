package com.example.android.benchmate;

public class Reagent {
    private String reagentID;//todo remove me if we are only using files, no db
    private String name;
    private UnitOfMeasure unitOfMeasure;

    public Reagent(String name) {
        this.name = name;
        //todo: not sure if it would be good to initialize a unitofmeasure
    }

    public Reagent() {//sometimes UI needs a null type
        //todo: revisit me after UI
    }

    public String getName() {
        return name;
    }

    public String printCSV() {
        return this.name + "," + "," + this.unitOfMeasure.name();
    }

    public String getReagentID() {
        return reagentID;
    }

    public void setReagentID(String reagentID) {
        this.reagentID = reagentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
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

    public static Reagent createFromCsvLine(String line) {
        Reagent reagent = new Reagent();
        String[] tokens = line.split(",", -1);//add "-1" to split method, since the comma is matched, but the empty match is not in the result without it

        reagent.setName(tokens[0]);
        try {
            reagent.setUnitOfMeasure(UnitOfMeasure.valueOf(tokens[1]));
        } catch (IllegalArgumentException ex) {
            //todo: enhance me for each field, with some error message and put null?
            reagent.setUnitOfMeasure(null);
        }
        //todo:implement me, parsing csv
        return reagent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Reagent{");
        sb.append("reagentID='").append(reagentID).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", unitOfMeasure=").append(unitOfMeasure);
        sb.append('}');
        return sb.toString();
    }
}
