package com.example.android.benchmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddReagent extends AppCompatActivity {

    private Button buttonOne, buttonTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reagent);

        buttonOne = (Button)  findViewById(R.id.buttonsave);
        buttonTwo = (Button)  findViewById(R.id.buttonback);

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetup();
            }
        });
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetup2();
            }
        });
    }
    public void openSetup(){
        Intent intent = new Intent(this, Setup.class);
        startActivity(intent);
    }
    public void openSetup2(){
        Intent intent = new Intent(this, Setup.class);
        startActivity(intent);
    }
}