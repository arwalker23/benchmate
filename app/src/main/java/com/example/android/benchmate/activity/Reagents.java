package com.example.android.benchmate.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.android.benchmate.R;

import java.util.ArrayList;

public class Reagents extends Activity implements CompoundButton.OnCheckedChangeListener {

    int flag = 0;
    private Button buttonBack, buttonSave;
    ArrayList<String> selectedReagents = new ArrayList<>();
    ArrayList<String> retrievedSelectedReagents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reagents);

        // Handle if instanceState is saved
        if (savedInstanceState != null) {
            retrievedSelectedReagents = savedInstanceState.getStringArrayList("checkedReagents");
            flag = savedInstanceState.getInt("savedflag");
        }

        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlate();
            }
        });

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reagents.this, Plate.class);
                getIntent().putStringArrayListExtra("selectedFields", selectedReagents);
                startActivity(intent);
            }
        });

        String[] reagents_array = getResources().getStringArray(R.array.units_array);
        CheckBox[] checkboxArray = new CheckBox[reagents_array.length];
        LinearLayout checkboxLayout = findViewById(R.id.chkboxlyt);

        for (int i = 0; i < checkboxArray.length; i++) {
            CheckBox checkbox = new CheckBox(getApplicationContext());
            checkbox.setText(reagents_array[i]);
            checkbox.setTextSize(28);
            checkboxArray[i] = checkbox;
            checkboxLayout.addView(checkbox);
            checkbox.setOnCheckedChangeListener(this);
        }

        // If there were previously saved checkboxes that the user ticked before, check them again
        if (flag != 0) {
            for (CheckBox checkbox : checkboxArray) {
                for (int i = 0; i < retrievedSelectedReagents.size(); i++) {
                    if ((checkbox.getText() + "").equals(retrievedSelectedReagents.get(i))) {
                        checkbox.toggle();
                    }
                }
            }
        }
    }

    public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
        String checkedText = cb.getText() + "";

        if (isChecked) {
            selectedReagents.add(checkedText);
            Toast.makeText(this, cb.getText() + " was selected!", Toast.LENGTH_SHORT).show();
        } else {
            selectedReagents.remove(checkedText);
            Toast.makeText(this, cb.getText() + " was not selected!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        flag = 1;
        savedState.putStringArrayList("selectedReagents", selectedReagents);
        savedState.putInt("savedflag", flag);
    }

    public void openPlate() {
        Intent intent = new Intent(this, Plate.class);
        startActivity(intent);
    }
}