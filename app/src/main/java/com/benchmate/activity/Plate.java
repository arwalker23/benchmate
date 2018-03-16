package com.benchmate.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.benchmate.R;

public class Plate extends AppCompatActivity {

    Button buttonSaveCSV, buttonNotes, buttonProcedure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate);

        buttonSaveCSV = findViewById(R.id.buttonSaveCSV);
        buttonNotes = findViewById(R.id.buttonNotes);
        buttonProcedure = findViewById(R.id.buttonProcedure);

        buttonNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNotes();
            }
        });
        buttonProcedure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProcedure();
            }
        });

        // TODO: save data from classes into a CSV file
        buttonSaveCSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Experiment state saved";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        String[] wellNames = getResources().getStringArray(R.array.well_names);
        GridLayout buttonLayout = findViewById(R.id.btnlyt);
        buttonLayout.setOrientation(GridLayout.VERTICAL);
        int i = 1;
        for (final String wellName : wellNames) {
            // Generate buttons within the GridLayout for every well in the well_names string resource
            Button btn = new Button(this);
            btn.setId(i);
            final int id_ = btn.getId();
            btn.setText(wellName);
            btn.setTextSize(16);
            btn.setBackgroundColor(Color.rgb(50, 205, 50));

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
                    openReagents();
                }

            });
            i++;
        }

    }

    public void openReagents() {
        Intent intent = new Intent(this, Reagents.class);
        startActivity(intent);
    }

//    public void openSettings() {
//        Intent intent = new Intent(this, Settings.class);
//        startActivity(intent);
//    }

    public void openNotes() {
        Intent intent = new Intent(this, Notes.class);
        startActivity(intent);
    }

    public void openProcedure() {
        Intent intent = new Intent(this, Procedure.class);
        startActivity(intent);
    }
}