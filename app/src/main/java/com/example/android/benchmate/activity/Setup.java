package com.example.android.benchmate.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.benchmate.domain.Experiment;
import com.example.android.benchmate.R;

public class Setup extends AppCompatActivity {

    private Button buttonAddReagent, buttonStart, buttonAddProcedure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setup);

        // Initialize experiment object to allow adding of new Reagent objects to the reagents list
        Experiment experiment = new Experiment();

        // test toast to show that object was indeed created and exists with experimentName = "unnamed"
//        Context context = getApplicationContext();
//        CharSequence text = "Test of " + experiment.getExperimentName();
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();

        buttonAddReagent = (Button) findViewById(R.id.buttonAddReagent);
        buttonAddProcedure = (Button) findViewById(R.id.buttonAddProcedure);
        buttonStart = (Button) findViewById(R.id.buttonStart);

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

    public void openAddReagent() {
        Intent intent = new Intent(this, AddReagent.class);
        startActivity(intent);
    }

    public void openPlate() {
        Intent intent = new Intent(this, Plate.class);
        startActivity(intent);
    }
}
