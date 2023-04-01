package com.example.counterhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_text);
        if (savedInstanceState != null) {
            editText.setText(savedInstanceState.getString("editText"));
        }
    }
    public void btnCount(View view) {
        TextView counterTextView = findViewById(R.id.counter_text_view);
        counter++;
        counterTextView.setText(String.valueOf(counter));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("editText", editText.getText().toString());
    }

}