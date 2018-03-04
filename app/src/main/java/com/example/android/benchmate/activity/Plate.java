package com.example.android.benchmate.activity;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.benchmate.R;

public class Plate extends AppCompatActivity {

    private Button buttonA, buttonSettings, buttonNotes, buttonProcedure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate);

        buttonA = (Button) findViewById(R.id.buttonA);
        buttonSettings = (Button) findViewById(R.id.buttonSettings);
        buttonNotes = (Button) findViewById(R.id.buttonNotes);
        buttonProcedure = (Button) findViewById(R.id.buttonProcedure);

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReagents();
            }
        });
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
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
    }

    public void openReagents() {
        Intent intent = new Intent(this, Reagents.class);
        startActivity(intent);
    }

    public void openSettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void openNotes() {
        Intent intent = new Intent(this, Notes.class);
        startActivity(intent);
    }

    public void openProcedure() {
        Intent intent = new Intent(this, Procedure.class);
        startActivity(intent);
    }
}