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

public class Setup extends AppCompatActivity {

    Button buttonAddReagent, buttonStart, buttonAddProcedure;
    EditText expNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final Experiment experiment;

        Intent intent = getIntent();
        if (intent.hasExtra("experiment")) {
            experiment = (Experiment) intent.getSerializableExtra("experiment");
        } else {
            experiment = new Experiment();
        }

//         Debug toast
//        Toast.makeText(this, "Test of " + experiment.getExperimentName(),
//                Toast.LENGTH_SHORT).show();

        expNameEditText = findViewById(R.id.expNameEditText);
        expNameEditText.setText(experiment.getExperimentName());
        buttonAddReagent = findViewById(R.id.buttonAddReagent);
        buttonAddProcedure = findViewById(R.id.buttonAddProcedure);
        buttonStart = findViewById(R.id.buttonStart);

        buttonAddReagent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setup.this, AddReagent.class);
                experiment.setExperimentName(expNameEditText.getText().toString());
                intent.putExtra("experiment", experiment);
                startActivity(intent);
            }
        });
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setup.this, Plate.class);
                experiment.setExperimentName(expNameEditText.getText().toString());
                experiment.initWells();
                intent.putExtra("experiment", experiment);
                startActivity(intent);
                finish();
            }
        });

        // TODO: implement openAddProcedure method and add AddProcedure screen if we want, or scrap procedure entirely
        //        buttonAddProcedure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openAddProcedure();
//            }
//        });
    }
}
