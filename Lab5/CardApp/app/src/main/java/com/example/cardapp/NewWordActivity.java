package com.example.cardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data.DatabaseHandler;
import model.Word;
import android.content.Intent;

public class NewWordActivity extends AppCompatActivity {

    EditText txtEng, txtMon;
    Button btnSave;
    DatabaseHandler DB;
    String fWord, mWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        txtEng = findViewById(R.id.txtEnglish);
        txtMon = findViewById(R.id.txtMongolia);
        btnSave = findViewById(R.id.btnSave);

        fWord = getIntent().getStringExtra("Fword");
        mWord = getIntent().getStringExtra("Mword");

        if(fWord!=null && mWord!=null){
            txtEng.setText(fWord);
            txtMon.setText(mWord);
        }

        DB = new DatabaseHandler(this);
    }

    public void onSaveBtn(View view) {
        String eng = txtEng.getText().toString();
        String mon = txtMon.getText().toString();

        if(eng.isEmpty() || mon.isEmpty()){
            Toast.makeText(this, "Please enter a word in both languages", Toast.LENGTH_SHORT).show();
            return;
        }

        String wordString = null;
        Word word = new Word(null);
        word.setF_word(eng);
        word.setM_word(mon);

        if(fWord!=null && mWord!=null){
            word.setItemId(Integer.parseInt(getIntent().getStringExtra("itemid")));
            boolean checker = DB.updateData(word);
            if(checker)
                Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
        }else{
            boolean checker = DB.insertData(word);
            if(checker)
                Toast.makeText(this, "Added successfully", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Add failed", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
//        finish();
    }

    public void onCancelBtn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
//        finish();
    }
}