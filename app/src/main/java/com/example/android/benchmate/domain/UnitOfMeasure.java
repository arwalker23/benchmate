package com.example.android.benchmate.domain;


public enum UnitOfMeasure {
    VOLUME_ML,
    WEIGHT_GR,
    MICROLITRE,
    UNKNOWN; //ui for null maybe
    public static UnitOfMeasure parse(String name) {
        try {
            return Enum.valueOf(UnitOfMeasure.class, name);
        } catch (Exception e) {
            return UNKNOWN;
        }

    }

}
