package com.example.threebuttonactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    public static final String EXTRA_MSG =  "first";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(this, SecondActivity.class);
        setContentView(R.layout.activity_main);
    }

    public void btnFirst(View view) {
        String msg = "Some people say that rats are ugly creatures. When they see a rat running along, they go ee-yuck! Well I don’t know about you, but I’ve always thought that this was rather rude. Rats can have hurt feelings too you know! In any case, when I catch sight of my reflection in a stream, I think I’m rather cute.";
        intent.putExtra(MainActivity.EXTRA_MSG, msg);
        startActivity(intent);
    }

    public void btnSecond(View view) {
        String msg = "When I got closer, I saw the King himself, riding along on top of a great fat lump of an elephant. The crowd of onlookers were ooo-ing and aah-ing full of admiration for that stupid beast with a nose that’s far too big for her face. She’s much uglier than me, I thought. So I started to spring up and down and say: “Hey everyone! Why not look at me? I’m such a cutie-pie! I could join the King’s household and be a royal rat, if only there was any justice in the world.”";
        intent.putExtra(MainActivity.EXTRA_MSG, msg);
        startActivity(intent);
    }

    public void btnThird(View view) {
        String msg = "At first, nobody noticed me. They were all too busy ogling that stupid elephant. Little did I know that riding behind the elephant in a carriage, was the princess, and she was holding a beastly cat in her arms. When he caught sight of me, the cat leapt out of the carriage and started to chase me. I had to run for my life, and popped down a hole just in time before the cat could eat me up.";
        intent.putExtra(MainActivity.EXTRA_MSG, msg);
        startActivity(intent);
    }
}