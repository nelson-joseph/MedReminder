/**
 * Created by Nelson Joseph on 26-May-2016.
 * Reg. No: 75814200014
 * Roll. No: 1406FOSS0011
 */

package com.medreminder;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.medreminder.database.MedicationLab;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMedication extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static AddMedication inst;
    private TextView alarmTextView;

    public static AddMedication instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    Medication medication = new Medication();
    MedicationLab medicationLab = null;
    String item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        final EditText editTextMedicationName = (EditText) findViewById(R.id.editTextMedicationName);
        //medication.setMedicineName(String.valueOf(editTextMedicationName.getText()));

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        final ArrayList<String> items = new ArrayList<>();
        items.add("Once a day");
        items.add("Twice a day");
        items.add("3 Times a day");
        items.add("4 Times a day");

        // Creating Adapter for spinner
        ArrayAdapter<String> arrayAdapterForSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        arrayAdapterForSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attaching arrayAdapterForSpinner to spinner
        spinner.setAdapter(arrayAdapterForSpinner);

        //Button buttonSetTime = (Button) findViewById(R.id.buttonsetTime);

        /*Button btnSetAlarm = (Button) findViewById(R.id.buttonsetTime);
        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                FragmentManager fragmentManager = getFragmentManager();
                //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                newFragment.show(fragmentManager, "timePicker");
            }
        });*/


        Button buttonOk = (Button) findViewById(R.id.buttonOk);
        //buttonOk.setOnCreateContextMenuListener(this);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String medicineName = editTextMedicationName.getText().toString();
                if (medicineName.isEmpty() || item.isEmpty())
                {
                    Toast.makeText(AddMedication.this, "Fill The Required fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    openOptionsMenu();
                    medication.getId();
                    medication.setMedicineName(editTextMedicationName.getText().toString());
                    medication.setDosage(item);
                    medication.setReminderTime("9 a.m");
                    medication.setInstructions("After food");
                    medication.setTotalNumberOfTablets("30");
                    medication.setRemindMeWhen("10");

                    medicationLab = MedicationLab.get(getApplicationContext());
                    medicationLab.addMedication(medication);

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
                    Intent myIntent = new Intent(AddMedication.this, AlarmReceiver.class);
                    pendingIntent = PendingIntent.getBroadcast(AddMedication.this, 0, myIntent, 0);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    Intent intent = new Intent(AddMedication.this, MedicationActivity.class);
                    startActivity(intent);
                    Toast.makeText(AddMedication.this, "Alarm Created Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button buttonCancel = (Button) findViewById(R.id.buttoncancel);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMedication.this, MedicationActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_medication, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void setAlarmText(String alarmText) {
        //alarmTextView.setText(alarmText);
    }

}