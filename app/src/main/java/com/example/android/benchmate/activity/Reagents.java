package com.example.android.benchmate.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.benchmate.R;

public class Reagents extends Activity {

    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reagents);
        buttonBack = (Button) findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlate();
            }
        });

    }

    public void openPlate() {
        Intent intent = new Intent(this, Plate.class);
        startActivity(intent);
    }
}