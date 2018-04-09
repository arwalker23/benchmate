package com.benchmate.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Experiment implements Serializable{
    private String experimentName;
    private List<Reagent> reagents;
    private TreeMap<String, Well> wells; // TODO: add method for adding new wells to map

    // TODO: remove this constructor if unused
//    public Experiment(String experimentName) {
//        this.experimentName = experimentName;
//        this.reagents = new ArrayList<>();
//    }

    // default constructor with no parameters that gets called when Setup screen is created, after
    // "New Experiment" button is pressed on main screen, or when experiment is loaded from file
    public Experiment() {
        this.experimentName = "unnamed";
        this.reagents = new ArrayList<>();
        this.wells = new TreeMap<>();
    }

    public boolean addReagent(Reagent reagent) {
        if (reagents == null) {
            reagents = new ArrayList<>();
        }
        if (reagents.contains(reagent)) {
            System.out.println("Reagent already exists. Duplicate reagents not allowed.");
            return false;
        }

        reagents.add(reagent);
        return true;
    }

    // TODO: remove
//    public void printExperiment() {
//        System.out.println("Printing experiment " + this.experimentName);
//        for (Reagent reagent : reagents) {
//            System.out.println(reagent.toString());
//        }
//    }

    public String getExperimentName() {
        return experimentName;
    }

    public List<Reagent> getReagents() {
        return reagents;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public void setReagents(List<Reagent> reagents) {
        this.reagents = reagents;
    }

    public TreeMap<String, Well> getWells() {
        return wells;
    }

    public void initWells() {
        ArrayList<Boolean> checkedReagents = new ArrayList<Boolean>();
        for(int i = 0; i < reagents.size(); i++){
            checkedReagents.add(false);
        }
        for(WellEnum well : WellEnum.values()){
            Well newWell = new Well(well.name(), checkedReagents);
            wells.put(well.name(), newWell);
        }
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
        sb.append(", experimentName='").append(experimentName).append('\'');
        sb.append(", reagents=").append(reagents);
        sb.append('}');
        return sb.toString();
    }
}
