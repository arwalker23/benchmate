package com.benchmate.activity;

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
//        Context context = getApplicationContext();
//        CharSequence text = "Experiment successfully passed: " + experiment.getExperimentName();
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();

        buttonSave = findViewById(R.id.buttonsave);
        buttonBack = findViewById(R.id.buttonback);

        // Create spinner for units dropdown selection
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
                // Use getText from text boxes and bind body into Reagent object fields
                String name = reagentName.getText().toString();

                double amount;
                String amountStr = reagentAmount.getText().toString();
                if (amountStr == null || amountStr.isEmpty()) {
                    amount = 0.0;
                } else {
                    amount = Double.parseDouble(reagentAmount.getText().toString());
                }

                String units = spinnerUnits.getSelectedItem().toString();
                Reagent reagent = new Reagent(name, amount, units);
                experiment.addReagent(reagent);

                // Toast to debug
//                Context context = getApplicationContext();
//                CharSequence text = "Reagent saved: " + reagent.getAmount() + " " + reagent.getUnitOfMeasure() + " " + reagent.getName();
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();

                // Pass Reagent object from onClick() back to Setup to add them to experiment class
                Intent intent = new Intent(AddReagent.this, Setup.class);
                intent.putExtra("experiment", experiment);
                startActivity(intent);
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddReagent.this, Setup.class);
                intent.putExtra("experiment", experiment);
                startActivity(intent);
            }
        });
    }
}
