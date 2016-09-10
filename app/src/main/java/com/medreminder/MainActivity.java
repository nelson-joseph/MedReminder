/**
 * Created by Nelson Joseph on 26-May-2016.
 * Reg. No: 75814200014
 * Roll. No: 1406FOSS0011
 */

package com.medreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.medreminder.database.MedicationLab;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private static MainActivity mainActivity;

    private TextView alarmTextView;
    TextClock textClock;

    final static int requestCode = 1;

    Medication medication = new Medication();
    MedicationLab medicationLab = null;

    public static MainActivity getMainActivityInstance() {
        return mainActivity;
    }

    @Override
    public void onStart() {
        super.onStart();
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textClock = (TextClock) findViewById(R.id.textClock);
        textClock.setFormat12Hour("dd/MMM/yyyy");

        //timePickerDialog = (TimePicker) findViewById(R.id.timePicker);
        //alarmTextView = (TextView) findViewById(R.id.alarmText);

        /* Request the AlarmManager object */
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        /*Button btnSetAlarm = (Button) findViewById(R.id.buttonSetAlarm);
        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                FragmentManager fragmentManager = getFragmentManager();
                //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                newFragment.show(fragmentManager, "timePicker");
            }
        });*/

        /*ImageButton buttonAfternoon = (ImageButton) findViewById(R.id.buttonAfternoon);

        buttonAfternoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alarmManager != null && pendingIntent != null) {
                    alarmManager.cancel(pendingIntent);
                    Snackbar.make(view, "Alarm cancelled", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                else {
                    Snackbar.make(view, "No Alarm set!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });*/

        /*Button buttonNight = (Button) findViewById(R.id.buttonNight);
        buttonNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Email sent", Toast.LENGTH_SHORT).show();

                medication.setMedicineName("Amoxciline");
                medication.setDosage("500mg");
                medication.setReminderTime("9 a.m");
                medication.setInstructions("After food");
                medication.setTotalNumberOfTablets("30");
                medication.setRemindMeWhen("10");

                medicationLab = MedicationLab.get(getApplicationContext());
                medicationLab.addMedication(medication);
            }
        });*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            moveTaskToBack(true);
            //super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_pill) {
            Intent medicationIntent = new Intent(MainActivity.this, MedicationActivity.class);
            medicationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(medicationIntent);
        }
        else if (id == R.id.nav_settings) {
            Intent settingsIntent = new Intent(MainActivity.this, Settings.class);
            //settingsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(settingsIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setAlarmText(String alarmText) {
        //alarmTextView.setText(alarmText);
    }

    public AlarmManager getAlarmManager() {
        return this.alarmManager;
    }

}