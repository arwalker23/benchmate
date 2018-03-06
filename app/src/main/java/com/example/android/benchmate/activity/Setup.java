package com.example.android.benchmate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.benchmate.R;
import com.example.android.benchmate.domain.Experiment;

public class Setup extends AppCompatActivity {

    Button buttonAddReagent, buttonStart, buttonAddProcedure;
    Experiment experiment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setup);

        // test toast to show that object was indeed created and exists with experimentName = "unnamed"
//        Context context = getApplicationContext();
//        CharSequence text = "Test of " + experiment.getExperimentName();
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();

        buttonAddReagent = findViewById(R.id.buttonAddReagent);
        buttonAddProcedure = findViewById(R.id.buttonAddProcedure);
        buttonStart = findViewById(R.id.buttonStart);

        buttonAddReagent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddReagent();
            }
        });
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlate();
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

    // TODO: modify intents to reside within buttons instead of external method

    // TODO: use intents to pass Experiment object to future screens
    public void openAddReagent() {
        Intent intent = new Intent(this, AddReagent.class);
        startActivity(intent);
    }

    public void openPlate() {
        Intent intent = new Intent(this, Plate.class);
        startActivity(intent);
    }
}
