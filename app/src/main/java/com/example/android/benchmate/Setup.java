package com.example.android.benchmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Setup extends AppCompatActivity {

    private Button buttonOne, buttonThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        // Initialize experiment object to allow adding of new Reagent objects to the reagents list
        Experiment experiment = new Experiment();

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
