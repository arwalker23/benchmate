package com.benchmate.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.benchmate.R;
import com.benchmate.domain.Experiment;

public class Setup extends AppCompatActivity {

    Button buttonAddReagent, buttonStart, buttonAddProcedure;
    EditText expNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setup);

        final Experiment experiment;

        Intent intent = getIntent();
        if (intent.hasExtra("experiment")) {
            experiment = (Experiment) intent.getSerializableExtra("experiment");
        } else {
            experiment = new Experiment();
        }

//         test toast to show that object was indeed created and exists with experimentName = "unnamed"
        Context context = getApplicationContext();
        CharSequence text = "Test of " + experiment.getExperimentName();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        expNameEditText = findViewById(R.id.expNameEditText);
        expNameEditText.setText(experiment.getExperimentName());
        buttonAddReagent = findViewById(R.id.buttonAddReagent);
        buttonAddProcedure = findViewById(R.id.buttonAddProcedure);
        buttonStart = findViewById(R.id.buttonStart);

        buttonAddReagent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setup.this, AddReagent.class);
                experiment.setExperimentName(expNameEditText.getText().toString());
                intent.putExtra("experiment", experiment);
                startActivity(intent);
            }
        });
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setup.this, Plate.class);
                experiment.setExperimentName(expNameEditText.getText().toString());
                intent.putExtra("experiment", experiment);
                startActivity(intent);
            }
        });

        // TODO: implement openAddProcedure method and add AddProcedure screen if we want, or scrap procedure entirely
        //        buttonAddProcedure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openAddProcedure();
//            }
//        });
    }
}
