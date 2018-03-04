package com.example.android.benchmate.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.benchmate.domain.Experiment;
import com.example.android.benchmate.R;

public class Setup extends AppCompatActivity {

    private Button buttonOne, buttonThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize experiment object to allow adding of new Reagent objects to the reagents list
        Experiment experiment = new Experiment();

        setContentView(R.layout.activity_setup);



        buttonOne = (Button)  findViewById(R.id.button1);
        // buttonTwo = (Button)  findViewById(R.id.button2);
        buttonThree = (Button)  findViewById(R.id.button3);

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddReagent();
            }
        });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlate();
            }
        });
    }
    public void openAddReagent(){
        Intent intent = new Intent(this, AddReagent.class);
        startActivity(intent);
    }
    public void openPlate(){
        Intent intent = new Intent(this, Plate.class);
        startActivity(intent);
    }
}
