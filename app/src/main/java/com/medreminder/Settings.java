package com.medreminder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    TextView edit;
    RelativeLayout editNum;
    AppConstants appConstants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        appConstants = new AppConstants();
        edit = (TextView)findViewById(R.id.number);
        editNum = (RelativeLayout) findViewById(R.id.edit_number_settings);
        appConstants.sharedPreferences = getSharedPreferences(appConstants.prefName, Context.MODE_PRIVATE);
        edit.setText(appConstants.sharedPreferences.getString(appConstants.number, null));
        editNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, AddNumber.class);
                startActivity(intent);
            }
        });
    }
}
