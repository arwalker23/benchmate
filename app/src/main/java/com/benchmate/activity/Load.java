package com.benchmate.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.benchmate.R;

public class Load extends AppCompatActivity {

    Button buttonLoad, buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        buttonLoad = findViewById(R.id.buttonLoad);
        buttonBack = findViewById(R.id.buttonBack);

// TODO: add Loading capability
//        buttonLoad.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPlate();
//            }
//        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }
    public void openPlate(){
        Intent intent = new Intent(this, Plate.class);
        startActivity(intent);
        finish();
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
