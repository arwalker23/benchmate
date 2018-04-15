package com.benchmate.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.benchmate.R;
import com.benchmate.domain.Experiment;

public class Notes extends AppCompatActivity {

    EditText notesText;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        final Experiment experiment = (Experiment) intent.getSerializableExtra("experiment");

        notesText = findViewById(R.id.notesText);

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notes.this, Plate.class);
                intent.putExtra("experiment", experiment);
                startActivity(intent);
                finish();
            }
        });
    }
}
