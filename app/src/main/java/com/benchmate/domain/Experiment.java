package com.benchmate.domain;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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

    // default constructor with no parameters that gets called when Setup screen is created, after
    // "New Experiment" button is pressed on main screen, or when experiment is loaded from file
    public Experiment() {
        this.experimentName = "";
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
        final String lineEnding = "\r\n"; //todo : maybe make me public static final LINE_ENDING out of here
        StringBuffer commas = new StringBuffer();
        String commaWellHeader = ","; //todo add one more if we add description( or wellName) as user input

        // Add reagent information in header
        for (Reagent reagent : reagents) {
            String reagentString = reagent.getAmount() + " " + reagent.getUnitOfMeasure() + " " + reagent.getName();
            commas.append(",");
            commas.append(reagentString);
        }

        StringBuffer wellsHeaderBuffer = new StringBuffer();
        wellsHeaderBuffer.append("Well").append(",");
        wellsHeaderBuffer.append("Well Name");
        wellsHeaderBuffer.append(commas.toString());
        wellsHeaderBuffer.append(lineEnding);
        String wellsHeader = wellsHeaderBuffer.toString();

        // Print CSV body, based on reagents TreeMap
        StringBuffer sbCsvBody = new StringBuffer();
        Well well;
        for (WellEnum wellEnum : WellEnum.values()) {
            String wellName = wellEnum.name();
            sbCsvBody.append(toCsvCell(wellName)).append(",");
            well = wells.get(wellName);
            sbCsvBody.append(well.getName()).append(",");
            ArrayList<Boolean> reagentsSelected = well.getSelectedReagents();
            addReagentColumns(lineEnding, sbCsvBody, reagentsSelected);
        }
        String directoryBasePath = ""; //get file internal storage path

        // Return the final output
        return (wellsHeader + sbCsvBody.toString()).getBytes("UTF-8");
    }

    private void addReagentColumns(String lineEnding, StringBuffer sb, ArrayList<Boolean> reagentsSelected) {
        for (Boolean selected : reagentsSelected) {
            // Put a 1 if reagent was added, else put a 0
            if (selected) {
                Reagent reagent = reagents.get(reagentsSelected.indexOf(selected));
                sb.append("1").append(",");
            } else {
                sb.append("0").append(",");
            }
        }
        sb.append(lineEnding);
    }

    /**
     * //todo put me in a CsvUtils class
     * generate  file Name
     *
     * @return the name of the file
     */
    public static String generateFileName(String fileNamePrefix, String extension) {
        String currentDate = format(Calendar.getInstance().getTime(), "_yyyyMMdd_HHmmss");
        return fileNamePrefix + currentDate + "." + extension;
    }

    //todo put me in a CsvUtils class
    public static StringBuffer toCsvCell(Object obj) {
        StringBuffer csvCell = new StringBuffer();
        if (obj != null) {
            csvCell.append("\"").append(obj).append("\"");
        }
        return csvCell;
    }

    //todo maybe put me in the same util class as CsvUtils, or DateUtils
    public static String format(Date date, String format) {
        String formattedDate = null;
        if (date != null) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
            formattedDate = dateFormatter.format(date);
        }
        return formattedDate;
    }

    /**
     * //todo put me in a CsvUtils class
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

    //todo put me in a CsvUtils class
    public static void writeDataToStream(OutputStream stream, byte[] content) throws IOException {
        BufferedOutputStream os = null;
        try {
            os = new BufferedOutputStream(new DataOutputStream(stream));
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

    /*
     * todo: use this method if you deliver the csv body as string not as byte[], writeToCsv
     * */
    public static void writeToFile(String outputPath, String csvContent) {
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath)));
            bufferedWriter.write(csvContent);
            bufferedWriter.close();
        } catch (FileNotFoundException ex) {
            //log or tell the exception to user
            ex.printStackTrace();
        } catch (IOException e) {
            //log or tell the exception to user
            e.printStackTrace();
        } finally {
            //Close the BufferedWriter
            try {
                if (bufferedWriter != null) {
                    // writer.flush();
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                //todo: decide how we handle this case.
                e.printStackTrace();
            }
        }
    }
}
