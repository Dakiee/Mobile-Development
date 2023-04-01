package com.example.cardapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import data.DatabaseHandler;
import model.Word;
import android.content.Intent;
import android.widget.Toast;
import android.view.View.OnLongClickListener;
public class MainActivity extends AppCompatActivity {
    ArrayList<Word> words;
    Button btnAdd, btnUpdate, btnDelete, btnBefore;
    TextView  txtEng, txtMon;
    SharedPreferences settingFile;
    DatabaseHandler DB;
    int indexCurrent = 0, updateWordIndex = -1, viewModeMain = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.addBtn);
        btnUpdate =  findViewById(R.id.updateBtn);
        btnDelete =  findViewById(R.id.deleteBtn);
        btnBefore =  findViewById(R.id.beforeBtn);
        txtEng =  findViewById(R.id.txtEnglish);
        txtMon =  findViewById(R.id.txtMongolia);
        settingFile = getSharedPreferences("appSettings", Context.MODE_PRIVATE);

        if(settingFile.getInt("viewMode", -1) == -1){
            SharedPreferences.Editor editor = settingFile.edit();
            editor.putInt("viewMode", 0);
            editor.apply();
        }else{
            viewModeMain = settingFile.getInt("viewMode", 0);
        }

        DB = new DatabaseHandler(this);
        words = DB.getWords();

        if(words.size()>0){
            displayWords();
        }
        else{
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
            txtEng.setText("No words detected");
            txtMon.setText("No words detected");
        }

        txtEng.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NewWordActivity.class);
                intent.putExtra("Fword", words.get(indexCurrent).getF_word());
                intent.putExtra("Mword", words.get(indexCurrent).getM_word());
                updateWordIndex = words.get(indexCurrent).getItemId();
                intent.putExtra("itemid", updateWordIndex + "");

                startActivityForResult(intent, 1);
                Toast.makeText(MainActivity.this, "Update", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        txtMon.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NewWordActivity.class);
                intent.putExtra("Fword", words.get(indexCurrent).getF_word());
                intent.putExtra("Mword", words.get(indexCurrent).getM_word());
                updateWordIndex = words.get(indexCurrent).getItemId();
                intent.putExtra("itemid", updateWordIndex + "");

                startActivityForResult(intent, 1);
                Toast.makeText(MainActivity.this, "Update", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
    private void displayWords() {
        if(words.size()==0){
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
            txtEng.setText("No words detected");
            txtMon.setText("No words detected");
            return;
        }
        if(viewModeMain==0){
            txtEng.setText(words.get(indexCurrent).getF_word());
            txtMon.setText(words.get(indexCurrent).getM_word());
        }
        else if(viewModeMain==1){
            txtEng.setText("");
            txtMon.setText(words.get(indexCurrent).getM_word());
        }
        else if(viewModeMain==2){
            txtEng.setText(words.get(indexCurrent).getF_word());
            txtMon.setText("");
        }

    }

    public void onAddBtn(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, NewWordActivity.class);
        startActivityForResult(intent, 1);
        Toast.makeText(MainActivity.this, "Add", Toast.LENGTH_SHORT).show();
    }

    public void onUpdateBtn(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, NewWordActivity.class);
        intent.putExtra("Fword", words.get(indexCurrent).getF_word());
        intent.putExtra("Mword", words.get(indexCurrent).getM_word());
        updateWordIndex = words.get(indexCurrent).getItemId();
        intent.putExtra("itemid", updateWordIndex + "");

        startActivityForResult(intent, 1);
        Toast.makeText(MainActivity.this, "Update", Toast.LENGTH_SHORT).show();
    }

    public void onDeleteBtn(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Word");
        builder.setMessage("Та энэ үгийг устгахдаа итгэлтэй байна уу?");
        builder.setPositiveButton("Устгах", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean checker = DB.deleteData(words.get(indexCurrent));
                if(checker){
                    Toast.makeText(MainActivity.this, words.get(indexCurrent).getF_word() + "- ug ustlaa.", Toast.LENGTH_SHORT).show();
                }
                indexCurrent--;
                indexCurrent--;
                if(indexCurrent<0){
                    indexCurrent=0;
                }
                words=DB.getWords();
                displayWords();
            }
        });
        builder.setNegativeButton("Цуцлах", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onBeforeBtn(View view) {
        indexCurrent--;
        if(indexCurrent<0){
            indexCurrent=words.size()-1;
        }
        displayWords();
    }

    public void onAfterBtn(View view) {
        indexCurrent++;
        if(words.size()<=indexCurrent){
            indexCurrent=0;
        }
        displayWords();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.settings) {
            Intent intent = new Intent(this, Settings.class);
            intent.putExtra("viewMode", viewModeMain);
            startActivityForResult(intent, 3);
        }
        else if(item.getItemId() == R.id.exceldata){
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            startActivityForResult(intent, 99);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3) {
            viewModeMain = data.getIntExtra("viewMode", -1);
            SharedPreferences.Editor editor = settingFile.edit();
            editor.putInt("viewMode", viewModeMain);
            editor.apply();
            displayWords();
        }
        if (requestCode == 99 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try{
                BufferedReader reader;
                InputStream is = getContentResolver().openInputStream(uri);
                reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String line;
                while((line = reader.readLine()) != null){
                    String[] ugs = line.split(",");
                    Word word = new Word(line);

                    word.setF_word(ugs[0]);
                    word.setM_word(ugs[1]);

                    DB.insertData(word);
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            }
            catch (Exception ignored){
                System.out.println("Error");
            }

        }
    }

    public void onToggleMongolian(View view) {
        txtMon.setText(words.get(indexCurrent).getM_word());

    }
    public void onToggleEnglish(View view) {
        txtEng.setText(words.get(indexCurrent).getF_word());
    }

}