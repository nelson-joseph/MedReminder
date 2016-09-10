package com.medreminder;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Alert extends AppCompatActivity {
    Button stop;
    AppConstants appConstants;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        appConstants = new AppConstants();
        mediaPlayer = MediaPlayer.create(this, R.raw.kalimba);
        mediaPlayer.start();
        sendSMS();
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
        stop = (Button)findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
            }
        });
    }

    private void sendSMS() {
        appConstants = new AppConstants();
        appConstants.sharedPreferences = getSharedPreferences(appConstants.prefName, Context.MODE_PRIVATE);
        String mobileNumber = appConstants.sharedPreferences.getString(appConstants.number, null);
        if (mobileNumber.isEmpty()) {
            Toast.makeText(Alert.this, "Kindly Set Recipient Number In Settings", Toast.LENGTH_SHORT).show();
        }
        else {
            String message = "This is the time to take your medicine \n\n -Send by MedReminder";
            //Toast.makeText(getApplicationContext(), mobileNumber, Toast.LENGTH_LONG).show();
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(mobileNumber, null, message, null, null);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Message Sending Failed", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }
    public void onBackPressed() {
        //moveTaskToBack(true);
        mediaPlayer.stop();
        finish();
        Intent intent = new Intent(Alert.this, MainActivity.class);
        startActivity(intent);

    }
}
