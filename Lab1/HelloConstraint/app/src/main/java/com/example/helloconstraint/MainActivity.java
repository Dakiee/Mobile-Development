package com.example.helloconstraint;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int mCount = 0, rnd_color, rnd_color2;
    TextView mShowCount;
    private Button btn_count;
    private Button btn_zero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        mShowCount = (TextView) findViewById(R.id.show_count);
        btn_count = (Button) findViewById(R.id.button_count);
        btn_zero = (Button) findViewById(R.id.button_zero);
    }
    public void showToast(View view){
        Toast.makeText(MainActivity.this, "Hello Toast" , Toast.LENGTH_SHORT).show();
    }

    public void CountUp(View view){
        mCount++;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));

        Random rnd = new Random();
        rnd_color = Color.argb(255, rnd.nextInt(255), rnd.nextInt(255),rnd.nextInt(255));
        rnd_color2 = Color.argb(255, rnd.nextInt(255), rnd.nextInt(255),rnd.nextInt(255));
        btn_count.setBackgroundColor(rnd_color);
        btn_zero.setBackgroundColor(rnd_color2);
    }
    public void setZero(View view) {
        mCount = 0;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));

        rnd_color2 = Color.argb(255, 128, 128, 128);
        btn_zero.setBackgroundColor(rnd_color2);
    }
}