package com.example.android.benchmate;


public class ExperimentKey {
    private String experimentName;
    private String reagentName;
    private Well well;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExperimentKey that = (ExperimentKey) o;

        if (experimentName != null ? !experimentName.equals(that.experimentName) : that.experimentName != null)
            return false;
        if (reagentName != null ? !reagentName.equals(that.reagentName) : that.reagentName != null)
            return false;
        return well == that.well;
    }

    @Override
    public int hashCode() {
        int result = experimentName != null ? experimentName.hashCode() : 0;
        result = 31 * result + (reagentName != null ? reagentName.hashCode() : 0);
        result = 31 * result + (well != null ? well.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExperimentKey{");
        sb.append("experimentName='").append(experimentName).append('\'');
        sb.append(", reagentName='").append(reagentName).append('\'');
        sb.append(", well=").append(well);
        sb.append('}');
        return sb.toString();
    }
}
