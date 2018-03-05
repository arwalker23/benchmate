package com.example.android.benchmate.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.android.benchmate.R;

public class AddReagent extends AppCompatActivity {

    private Button buttonSave, buttonBack;
    private Spinner spinnerUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reagent);

        buttonSave = (Button)  findViewById(R.id.buttonsave);
        buttonBack = (Button)  findViewById(R.id.buttonback);

        // Create spinner for units dropdown selection
        spinnerUnits = (Spinner) findViewById(R.id.spinnerUnits);
        // Create ArrayAdapter using the units_array and default layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnits.setAdapter(adapter);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetup();
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
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
