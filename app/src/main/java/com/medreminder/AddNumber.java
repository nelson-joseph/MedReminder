package com.medreminder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNumber extends AppCompatActivity {
    EditText editNumber;
    Button saveChanges;
    AppConstants appConstants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number);
        editNumber = (EditText)findViewById(R.id.edit_number);
        saveChanges = (Button)findViewById(R.id.save_changes);
        appConstants = new AppConstants();
        appConstants.sharedPreferences = getSharedPreferences(appConstants.prefName, Context.MODE_APPEND);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = appConstants.sharedPreferences.edit();
                editor.putString(appConstants.number, editNumber.getText().toString());
                editor.commit();
                Intent intent = new Intent(AddNumber.this, Settings.class);
                startActivity(intent);
            }

            });

    }
}
