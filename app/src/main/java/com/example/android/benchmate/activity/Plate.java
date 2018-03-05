package com.example.android.benchmate.activity;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.benchmate.R;

public class Plate extends AppCompatActivity {

    private Button buttonA, buttonSaveCSV, buttonNotes, buttonProcedure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate);

        buttonA = (Button) findViewById(R.id.buttonA);
        buttonSaveCSV = (Button) findViewById(R.id.buttonSaveCSV);
        buttonNotes = (Button) findViewById(R.id.buttonNotes);
        buttonProcedure = (Button) findViewById(R.id.buttonProcedure);

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReagents();
            }
        });
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
                CharSequence text = "Saving experiment state...";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
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