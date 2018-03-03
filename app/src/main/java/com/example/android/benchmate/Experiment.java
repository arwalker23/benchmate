package com.example.android.benchmate;

import java.util.ArrayList;
import java.util.List;

public class Experiment {
    private String experimentID;//todo remove me if we are only using files, no db
    private String experimentName;
    private List<Reagent> reagents;


    public Experiment(String experimentID, String experimentName) {
        this.experimentName = experimentName;
        this.experimentID = experimentID;
        this.reagents = new ArrayList<>();
    }

    public boolean addReagent(Reagent reagent, UnitOfMeasure unitOfMeasure) {
        if (reagents == null) {
            reagents = new ArrayList<>();
        }
        if (reagents.contains(reagent)) {
            System.out.println("Reagent already exists. Duplicate reagents not allowed.");
            return false;
        }

        reagent.setUnitOfMeasure(unitOfMeasure);
        reagents.add(reagent);
        return true;
    }

    public void printExperiment() {
        System.out.println("Printing experiment " + this.experimentName);
        for (Reagent reagent : reagents) {
            System.out.println(reagent.toString());
        }
    }

    public String getExperimentName() {
        return experimentName;
    }

    public List<Reagent> getReagents() {
        return reagents;
    }

    public String getExperimentID() {
        return experimentID;
    }

    public void setExperimentID(String experimentID) {
        this.experimentID = experimentID;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public void setReagents(List<Reagent> reagents) {
        this.reagents = reagents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experiment that = (Experiment) o;

        return experimentName != null ? experimentName.equals(that.experimentName) : that.experimentName == null;
    }

    @Override
    public int hashCode() {
        return experimentName != null ? experimentName.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Experiment{");
        sb.append("experimentID='").append(experimentID).append('\'');
        sb.append(", experimentName='").append(experimentName).append('\'');
        sb.append(", reagents=").append(reagents);
        sb.append('}');
        return sb.toString();
    }
}
