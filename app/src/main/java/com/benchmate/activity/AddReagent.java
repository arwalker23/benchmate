package com.benchmate.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.benchmate.R;
import com.benchmate.domain.Experiment;
import com.benchmate.domain.Reagent;

public class AddReagent extends AppCompatActivity {

    Button buttonSave, buttonBack;
    EditText reagentName, reagentAmount;
    Spinner spinnerUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reagent);

//        Retrieve experiment object from intent
        Intent intent = getIntent();
        final Experiment experiment = (Experiment) intent.getSerializableExtra("experiment");

        // Toast to debug
//        Toast.makeText(this, "Experiment successfully passed: " + experiment.getExperimentName(),
//                Toast.LENGTH_SHORT).show();

        buttonSave = findViewById(R.id.buttonsave);
        buttonBack = findViewById(R.id.buttonback);
        spinnerUnits = findViewById(R.id.spinnerUnits);

        // Create ArrayAdapter using the units_array and default layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnits.setAdapter(adapter);

//        Create EditText objects
        reagentName = findViewById(R.id.reagentName);
        reagentAmount = findViewById(R.id.reagentAmount);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                // Get values from text boxes
                String name = reagentName.getText().toString();
                double amount;
                String amountStr = reagentAmount.getText().toString();
                String units = spinnerUnits.getSelectedItem().toString();

                // Validate that they are not empty
                if (name.isEmpty()) {
                    reagentName.setError("Please enter a reagent name.");
                    if (amountStr.isEmpty()) {
                        reagentAmount.setError("Please enter an amount.");
                    }
                }  else {
                    amount = Double.parseDouble(reagentAmount.getText().toString());
                    Reagent reagent = new Reagent(name, amount, units);
                    experiment.addReagent(reagent);

                    // Pass experiment object back
                    Intent intent = new Intent(AddReagent.this, Setup.class);
                    intent.putExtra("experiment", experiment);
                    startActivity(intent);
                    finish();
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddReagent.this, Setup.class);
                intent.putExtra("experiment", experiment);
                startActivity(intent);
                finish();
            }
        });
    }
}
