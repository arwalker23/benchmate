package com.benchmate.domain;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public class Experiment implements Serializable {
    private String experimentName;
    private List<Reagent> reagents;
    private TreeMap<String, Well> wells;
    private String notes;
    public static final String LINE_ENDING = "\r\n";

    // default constructor with no parameters that gets called when Setup screen is created, after
    // "New Experiment" button is pressed on main screen, or when experiment is loaded from file
    public Experiment() {
        this.experimentName = "";
        this.reagents = new ArrayList<>();
        this.wells = new TreeMap<>();
        this.notes = "";
    }

    public boolean addReagent(Reagent reagent) {
        if (reagents == null) {
            reagents = new ArrayList<>();
        }
        // Check for duplicate reagents
        if (reagents.contains(reagent)) {
            return false;
        }
        reagents.add(reagent);
        return true;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public List<Reagent> getReagents() {
        return reagents;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public TreeMap<String, Well> getWells() {
        return wells;
    }

    public void initWells() {
        ArrayList<Boolean> checkedReagents = new ArrayList<Boolean>();
        for (int i = 0; i < reagents.size(); i++) {
            checkedReagents.add(false);
        }
        for (WellEnum well : WellEnum.values()) {
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

    public byte[] writeToCsv() throws IOException {

        // Add reagent information in header
        StringBuffer commas = new StringBuffer();
        for (Reagent reagent : reagents) {
            String reagentString = reagent.getAmount() + " " + reagent.getUnitOfMeasure() + " " + reagent.getName();
            commas.append(",");
            commas.append(reagentString);
        }

        // Write CSV header
        StringBuffer wellsHeaderBuffer = new StringBuffer();
        wellsHeaderBuffer.append("Well").append(",");
        wellsHeaderBuffer.append("Well Name");
        wellsHeaderBuffer.append(commas.toString());
        wellsHeaderBuffer.append(LINE_ENDING);
        String wellsHeader = wellsHeaderBuffer.toString();

        // Write CSV body, based on reagents TreeMap
        StringBuffer sbCsvBody = new StringBuffer();
        Well well;
        for (WellEnum wellEnum : WellEnum.values()) {
            String wellName = wellEnum.name();
            sbCsvBody.append(toCsvCell(wellName)).append(",");
            well = wells.get(wellName);
            sbCsvBody.append(well.getName()).append(",");
            ArrayList<Boolean> reagentsSelected = well.getSelectedReagents();
            addReagentColumns(LINE_ENDING, sbCsvBody, reagentsSelected);
        }
        String experimentNotes = "\"" + notes + "\"";
        sbCsvBody.append(LINE_ENDING).append(experimentNotes);

        // Return the final output
        return (wellsHeader + sbCsvBody.toString()).getBytes("UTF-8");
    }

    private void addReagentColumns(String lineEnding, StringBuffer sb, ArrayList<Boolean> reagentsSelected) {
        for (Boolean selected : reagentsSelected) {
            // Put a 1 if reagent was added, else put a 0
            if (selected) {
                sb.append("1").append(",");
            } else {
                sb.append("0").append(",");
            }
        }
        sb.append(lineEnding);
    }

    /**
     *
     * generate  file Name
     *
     * @return the name of the file
     */
    public static String generateFileName(String fileNamePrefix, String extension) {
        String currentDate = format(Calendar.getInstance().getTime(), "_yyyyMMdd_HHmmss");
        return fileNamePrefix + currentDate + "." + extension;
    }

    public static StringBuffer toCsvCell(Object obj) {
        StringBuffer csvCell = new StringBuffer();
        if (obj != null) {
            csvCell.append("\"").append(obj).append("\"");
        }
        return csvCell;
    }

    public static String format(Date date, String format) {
        String formattedDate = null;
        if (date != null) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
            formattedDate = dateFormatter.format(date);
        }
        return formattedDate;
    }

    /**
     *
     * Writes the content to a file specified by a full path
     */
    public static void writeDataToFile(String filePath, byte[] content) throws IOException {
        BufferedOutputStream os = null;
        try {
            os = new BufferedOutputStream(new DataOutputStream(new FileOutputStream(filePath)));
            os.write(content);
            os.flush();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
