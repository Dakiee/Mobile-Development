package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.Intent;
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
    public void btnCheese(View view) {
        returnItem("Cheese");
    }

    public void btnRice(View view) {
        returnItem("Rice");

    }

    public void btnApple(View view) {
        returnItem("Apples");

    }

    private void returnItem(String item) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("item", item);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}