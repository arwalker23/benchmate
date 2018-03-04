package com.example.android.benchmate;

public class ExperimentRow {
    private ExperimentKey key;
    private UnitOfMeasure reagentUnitOfMeasure;
    private String notes;
    private Double amount;//todo: could be BigDecimal?

    public ExperimentKey getKey() {
        return key;
    }

    public void setKey(ExperimentKey key) {
        this.key = key;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public UnitOfMeasure getReagentUnitOfMeasure() {
        return reagentUnitOfMeasure;
    }

    public void setReagentUnitOfMeasure(UnitOfMeasure reagentUnitOfMeasure) {
        this.reagentUnitOfMeasure = reagentUnitOfMeasure;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public static ExperimentRow createFromCsvLine(String line){
        ExperimentRow row = new ExperimentRow();
        ExperimentKey key = new ExperimentKey();
        String[] tokens = line.split(",", -1);//add "-1" to split method, since the comma is matched, but the empty match is not in the result without it

        key.setExperimentName(tokens[0]);
        key.setReagentName(tokens[1]);
        key.setWell(Well.fromName(tokens[2]));
        row.setKey(key);
        try {
            row.setAmount(Double.parseDouble(tokens[3]));
        }catch(NumberFormatException ex){
            row.setAmount(null); // can be initialized as 0.0, or new Double("0.0");
        }
        row.setReagentUnitOfMeasure(UnitOfMeasure.parse(tokens[4]));
        row.setNotes(tokens[5]);// if there are notes when a combination of reagent and amounts are created
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExperimentRow that = (ExperimentRow) o;

        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExperimentRow{");
        sb.append("key=").append(key);
        sb.append(", reagentUnitOfMeasure=").append(reagentUnitOfMeasure);
        sb.append(", notes='").append(notes).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
