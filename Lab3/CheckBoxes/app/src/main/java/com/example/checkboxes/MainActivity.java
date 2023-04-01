package com.example.checkboxes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String msg;
    public CheckBox box1, box2 ,box3, box4, box5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         box1 = findViewById(R.id.checkBox6);
         box2 = findViewById(R.id.checkBox);
         box3 = findViewById(R.id.checkBox7);
         box4 = findViewById(R.id.checkBox8);
         box5 = findViewById(R.id.checkBox9);
    }

    public void onClick(View view) {
        ArrayList<String> checkedBoxes = new ArrayList<>();
        if (box1.isChecked()) {
            checkedBoxes.add("Chocolate Syrup");
        }
        if (box2.isChecked()) {
            checkedBoxes.add("Sprinkles");
        }
        if (box3.isChecked()) {
            checkedBoxes.add("Crushed nuts");
        }
        if (box4.isChecked()) {
            checkedBoxes.add("Cherries");
        }
        if (box5.isChecked()) {
            checkedBoxes.add("Oreo");
        }
        if (checkedBoxes.isEmpty()) {
            Toast.makeText(this, "Check box songoogui baina", Toast.LENGTH_SHORT).show();
        }
        else {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < checkedBoxes.size(); i++) {
                if (i > 0) {
                    str.append(", ");
                }
                str.append(checkedBoxes.get(i));
            }
            Toast.makeText(this,  "Toppings: " + str.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}