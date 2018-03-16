package com.benchmate.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.benchmate.R;
import com.benchmate.domain.Reagent;

public class AddReagent extends AppCompatActivity {

    Button buttonSave, buttonBack;
    Spinner spinnerUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reagent);

        Reagent reagent = new Reagent();

        // TODO: implement save reagent activity using data binding library
        buttonSave = findViewById(R.id.buttonsave);
        buttonBack = findViewById(R.id.buttonback);

        // Create spinner for units dropdown selection
        spinnerUnits = findViewById(R.id.spinnerUnits);
        // Create ArrayAdapter using the units_array and default layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnits.setAdapter(adapter);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: implement getText from text boxes and bind body into Reagent object fields

                // TODO: pass Reagent object from onClick() back to Setup to add them to experiment class
                Intent intent = new Intent(AddReagent.this, Setup.class);
                startActivity(intent);

                Context context = getApplicationContext();
                CharSequence text = "Reagent saved.";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelReagent();
            }
        });
    }

    public void cancelReagent(){
        Intent intent = new Intent(this, Setup.class);
        startActivity(intent);
    }
}
