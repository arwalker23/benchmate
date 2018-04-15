package com.benchmate.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.benchmate.R;
import com.benchmate.domain.Experiment;
import com.benchmate.domain.Reagent;

import java.util.ArrayList;
import java.util.List;

public class Reagents extends Activity implements CompoundButton.OnCheckedChangeListener {

    int flag = 0;
    Button buttonBack, buttonSave;
    ArrayList<Boolean> checkedReagents;
    ArrayList<CheckBox> checkboxArray = new ArrayList<>();
    EditText wellLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reagents);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Retrieve experiment object from intent
        Intent intent = getIntent();
        final Experiment experiment = (Experiment) intent.getSerializableExtra("experiment");

        // Retrieve name of well so Reagents knows which Well object to modify
        final String wellName = intent.getStringExtra("wellName");

        // Retrieve checked boxes from experiment class TreeMap "wells"
        checkedReagents = new ArrayList<>(experiment.getWells().get(wellName).getSelectedReagents());

        // Handle if instanceState is saved
        if (savedInstanceState != null) {
            checkedReagents = (ArrayList<Boolean>) savedInstanceState.getSerializable("checkedReagents");
            flag = savedInstanceState.getInt("savedflag");
        }

        wellLabel = findViewById(R.id.wellName);
        wellLabel.setText(experiment.getWells().get(wellName).getName());

        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reagents.this, Plate.class);
                intent.putExtra("experiment", experiment);
                startActivity(intent);
                finish();
            }
        });

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reagents.this, Plate.class);
                // Handling for selectedFields array, can update the Experiment object TreeMap "wells" accordingly
                experiment.getWells().get(wellName).setSelectedReagents(checkedReagents);
                experiment.getWells().get(wellName).setName(wellLabel.getText().toString());
                intent.putExtra("experiment", experiment);
                startActivity(intent);
                finish();
            }
        });

        List<Reagent> reagents_array = experiment.getReagents();
        LinearLayout checkboxLayout = findViewById(R.id.chkboxlyt);

        // Generate checkboxes for each reagent
        for (int i = 0; i < reagents_array.size(); i++) {
            CheckBox checkbox = new CheckBox(getApplicationContext());
            checkbox.setText(reagents_array.get(i).prettyPrint());
            checkbox.setTextSize(28);
            checkboxArray.add(checkbox);
            checkbox.setId(i);
            checkboxLayout.addView(checkbox);
            checkbox.setOnCheckedChangeListener(this);
            checkbox.setChecked(checkedReagents.get(i));
        }

        // If there were previously saved checkboxes that the user ticked before, check them again
        if (flag != 0) {
            for (int i = 0; i < checkboxArray.size(); i++) {
                checkboxArray.get(i).setChecked(checkedReagents.get(i));
            }
        }
    }

    public void onCheckedChanged(CompoundButton checkbox, boolean isChecked) {
        if (isChecked) {
            checkedReagents.set(checkbox.getId(), true);
        } else {
            checkedReagents.set(checkbox.getId(), false);
        }
    }

    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        flag = 1;
        savedState.putSerializable("checkedReagents", checkedReagents);
//        savedState.putStringArrayList("selectedReagents", selectedReagents);
        savedState.putInt("savedflag", flag);
    }
}