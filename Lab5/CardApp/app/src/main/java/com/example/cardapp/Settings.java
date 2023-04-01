package com.example.cardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Settings extends AppCompatActivity {

    SharedPreferences settingFile;
    int viewMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingFile = getSharedPreferences("appSettings", Context.MODE_PRIVATE);
        viewMode = settingFile.getInt("viewMode", 0);

        RadioButton rbBoth = findViewById(R.id.rbBoth);
        RadioButton rbOnlyMongolian = findViewById(R.id.rbOnlyMongolian);
        RadioButton rbOnlyEnglish = findViewById(R.id.rbOnlyEnglish);

        if (viewMode == 0) {
            rbBoth.setChecked(true);
        } else if (viewMode == 1) {
            rbOnlyMongolian.setChecked(true);
        } else if (viewMode == 2) {
            rbOnlyEnglish.setChecked(true);
        }
    }

    public void onSaveSettings(View view) {
        RadioButton rbBoth = findViewById(R.id.rbBoth);
        RadioButton rbOnlyMongolian = findViewById(R.id.rbOnlyMongolian);
        RadioButton rbOnlyEnglish = findViewById(R.id.rbOnlyEnglish);

        if (rbBoth.isChecked()) {
            viewMode = 0;
        } else if (rbOnlyMongolian.isChecked()) {
            viewMode = 1;
        } else if (rbOnlyEnglish.isChecked()) {
            viewMode = 2;
        }

        SharedPreferences.Editor editor = settingFile.edit();
        editor.putInt("viewMode", viewMode);
        editor.apply();

        setResult(RESULT_OK, getIntent().putExtra("viewMode", viewMode));
        finish();
    }

    public void onCancel(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}