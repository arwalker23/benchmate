package com.benchmate.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.benchmate.R;
import com.benchmate.domain.Experiment;

import java.io.File;

public class Plate extends AppCompatActivity {

    Button buttonSaveCSV, buttonNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Retrieve experiment object from intent
        Intent intent = getIntent();
        final Experiment experiment = (Experiment) intent.getSerializableExtra("experiment");

        buttonSaveCSV = findViewById(R.id.buttonSaveCSV);
        buttonNotes = findViewById(R.id.buttonNotes);

        buttonNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plate.this, Notes.class);
                intent.putExtra("experiment", experiment);
                startActivity(intent);
                finish();
            }
        });

        buttonSaveCSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                File file = getExternalFilesDir(null);
                String directoryBasePath = file.getPath();
                String filePath = directoryBasePath + "/" + experiment.generateFileName(experiment.getExperimentName(), "csv");
                try {
                    experiment.writeDataToFile(filePath, experiment.writeToCsv());
                    Toast.makeText(context, "Experiment state saved to " + filePath, Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(context, "Unable to save.", Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }
            }
        });

        String[] wellNames = getResources().getStringArray(R.array.well_names);
        GridLayout buttonLayout = findViewById(R.id.btnlyt);
        buttonLayout.setOrientation(GridLayout.VERTICAL);
        // Generate buttons within the GridLayout for every well in the well_names string resource
        int i = 1;
        for (final String wellName : wellNames) {
            Button btn = new Button(this);
            btn.setId(i);
            final int id_ = btn.getId();
            // Set button text and size
            btn.setText(wellName);
            btn.setTextSize(16);

            // Set button colour based on whether reagents have been checked or not
            if (experiment.getWells().get(wellName).getSelectedReagents().contains(true)) {
                if (experiment.getWells().get(wellName).getSelectedReagents().contains(false)) {
                    btn.setBackgroundColor(Color.rgb(255, 255, 0));
                } else {
                    btn.setBackgroundColor(Color.rgb(50, 205, 50));
                }
            } else {
                btn.setBackgroundColor(Color.rgb(255, 0, 0));
            }

            // Add to layout with custom parameters (size)
            buttonLayout.addView(btn);
            GridLayout.LayoutParams params = (GridLayout.LayoutParams) btn.getLayoutParams();
            params.height = 115;
            params.width = 115;
            btn.setLayoutParams(params);
            Button btn1 = findViewById(id_);

            // Set onClickListener for every button as it's generated
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            "Opening well " + wellName, Toast.LENGTH_SHORT)
                            .show();
                    Intent intent = new Intent(Plate.this, Reagents.class);
                    intent.putExtra("experiment", experiment);
                    intent.putExtra("wellName", wellName);
                    startActivity(intent);
                    finish();
                }

            });
            i++;
        }
    }
}