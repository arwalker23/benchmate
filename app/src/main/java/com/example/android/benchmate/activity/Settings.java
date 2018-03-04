package com.example.android.benchmate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.benchmate.R;

public class Settings extends AppCompatActivity {

    private Button buttonTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        buttonTwo = (Button) findViewById(R.id.buttonBack);


        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlate();
            }
        });

    }

    public void openPlate() {
        Intent intent = new Intent(this, Plate.class);
        startActivity(intent);}
}