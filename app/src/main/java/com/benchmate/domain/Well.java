package com.benchmate.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mihai on 4/7/2018.
 */

public class Well implements Serializable{
    private String name;
    private ArrayList<Boolean> selectedReagents;

    // TODO: modify constructor for Well to work with Reagents screen save button

    public Well(String name, ArrayList<Boolean> selectedReagents) {
        this.name = name;
        this.selectedReagents = selectedReagents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Boolean> getSelectedReagents() {
        return selectedReagents;
    }

    public void setSelectedReagents(ArrayList<Boolean> selectedReagents) {
        this.selectedReagents = selectedReagents;
    }
}
