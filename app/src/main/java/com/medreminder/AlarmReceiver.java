/**
 * Created by Nelson Joseph on 26-May-2016.
 * Reg. No: 75814200014
 * Roll. No: 1406FOSS0011
 */

package com.medreminder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.telephony.SmsManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmReceiver extends WakefulBroadcastReceiver {

    Context context;
    MainActivity mainActivity;
    AppConstants appConstants;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;
        AddMedication inst = AddMedication.instance();
        inst.setAlarmText("Alarm! Wake up! Wake up!");

        //this will sound the alarm tone
        //this will sound the alarm once, if you wish to
        //raise alarm in loop continuously then use MediaPlayer and setLooping(true)
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        //ringtone.play();

        //this will send a notification message
        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
        //sendEmail();
        //sendSMS();
    }

    protected void sendEmail() {
        String toEmail = "nelson@einnel.com";
        String ccEmail = "nelson@einnel.com";

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, toEmail);
        emailIntent.putExtra(Intent.EXTRA_CC, ccEmail);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Medication Reminder");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "This is the time to take your medicine");

        try {
            //startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            //mainActivity.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Toast.makeText(context, "Mail Sent", Toast.LENGTH_LONG).show();
            //mainActivity.finish();
        }
        catch (android.content.ActivityNotFoundException ex) {
            //Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void sendSMS() {
        appConstants = new AppConstants();
        appConstants.sharedPreferences = context.getSharedPreferences(appConstants.prefName, Context.MODE_PRIVATE);
        String mobileNumber = appConstants.sharedPreferences.getString(appConstants.number, null);
        String message = "This is the time to take your medicine";
        Toast.makeText(context, mobileNumber, Toast.LENGTH_LONG).show();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(mobileNumber, null, message, null, null);
        }
        catch (Exception e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}