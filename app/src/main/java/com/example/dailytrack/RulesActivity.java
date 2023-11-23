package com.example.dailytrack;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;
import java.util.concurrent.BlockingDeque;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        EditText edtInTIme = findViewById(R.id.editTextInTime);
        EditText edtOutTime = findViewById(R.id.editTextOutTime);
        Switch switchMon = findViewById(R.id.idSwitchMon);
        Switch switchTues = findViewById(R.id.idSwitchTues);
        Switch switchWed = findViewById(R.id.idSwitchWed);
        Switch switchThrus = findViewById(R.id.idSwitchThrus);
        Switch switchFri = findViewById(R.id.idSwitchFri);
        Switch switchSat = findViewById(R.id.idSwitchSat);
        Switch switchSun = findViewById(R.id.idSwitchSun);

        Button buttonSet = findViewById(R.id.buttonSet);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button buttonCancle = findViewById(R.id.buttonCancle);

        edtInTIme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(RulesActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                edtInTIme.setText(hourOfDay + ":" + minute);
                            }

                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();
            }
        });

        edtOutTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(RulesActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                edtOutTime.setText(hourOfDay + ":" + minute);
                            }

                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();
            }
        });

        switchEvent(switchMon);
        switchEvent(switchTues);
        switchEvent(switchWed);
        switchEvent(switchThrus);
        switchEvent(switchFri);
        switchEvent(switchSat);
        switchEvent(switchSun);


        buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set the setting in the data base


            }
        });

        buttonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RulesActivity.this,HomeActivity.class));
            }
        });

    }

    public void switchEvent(Switch switchDay){
        // on below line we are checking
        // the status of switch
        if (switchDay.isChecked()) {
            // on below line we are setting text
            // if switch is checked.
            Toast.makeText(RulesActivity.this, ""+switchDay.getText()+" is checked", Toast.LENGTH_SHORT).show();
        } else {
            // on below line we are setting the text
            // if switch is un checked
            Toast.makeText(RulesActivity.this, ""+switchDay.getText()+" is unchecked", Toast.LENGTH_SHORT).show();
        }

        // on below line we are adding check change listener for our switch.
        switchDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // on below line we are checking
                // if switch is checked or not.
                if (isChecked) {
                    // on below line we are setting text
                    // if switch is checked.
                    Toast.makeText(RulesActivity.this, ""+switchDay.getText()+" is checked", Toast.LENGTH_SHORT).show();
                } else {
                    // on below line we are setting text
                    // if switch is unchecked.
                    Toast.makeText(RulesActivity.this, ""+switchDay.getText()+" is unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}