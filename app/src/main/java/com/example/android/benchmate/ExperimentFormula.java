package com.example.android.benchmate;

public class ExperimentFormula {
    private ExperimentKey key;
    private double reagentUnits;
    private String notes;
    private double amount;//todo: could be float?, FLOAT, DOUBLE

    public ExperimentKey getKey() {
        return key;
    }

    public void setKey(ExperimentKey key) {
        this.key = key;
    }

    public double getReagentUnits() {
        return reagentUnits;
    }

    public void setReagentUnits(double reagentUnits) {
        this.reagentUnits = reagentUnits;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static ExperimentFormula createFromCsvLine(String line) {
        ExperimentFormula formula = new ExperimentFormula();

        //todo: implement me //first the key and then the rest
        return formula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExperimentFormula that = (ExperimentFormula) o;

        if (Double.compare(that.reagentUnits, reagentUnits) != 0) return false;
        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = key != null ? key.hashCode() : 0;
        temp = Double.doubleToLongBits(reagentUnits);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExperimentFormula{");
        sb.append("key=").append(key);
        sb.append(", reagentUnits=").append(reagentUnits);
        sb.append(", notes='").append(notes).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
