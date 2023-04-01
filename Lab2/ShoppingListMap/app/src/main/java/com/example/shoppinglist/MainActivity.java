package com.example.shoppinglist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final TextView[] textViews = new TextView[10];
    private EditText storeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViews[0] = findViewById(R.id.textView1);
        textViews[1] = findViewById(R.id.textView2);
        textViews[2] = findViewById(R.id.textView3);
        textViews[3] = findViewById(R.id.textView4);
        textViews[4] = findViewById(R.id.textView5);
        textViews[5] = findViewById(R.id.textView6);
        textViews[6] = findViewById(R.id.textView7);
        textViews[7] = findViewById(R.id.textView8);
        textViews[8] = findViewById(R.id.textView9);
        textViews[9] = findViewById(R.id.textView10);

        storeEditText = findViewById(R.id.store_edit_text);
    }

    public void openShoppingList(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String item = data.getStringExtra("item");
            for (TextView textView : textViews) {
                if (textView.getText().toString().isEmpty()) {
                    textView.setText(item);
                    break;
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for (int i = 0; i < textViews.length; i++) {
            outState.putString("item" + i, textViews[i].getText().toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setText(savedInstanceState.getString("item" + i));
        }
    }

    public void searchStore(View view) {
        String storeName = storeEditText.getText().toString();
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + storeName));
        startActivity(mapIntent);
    }
}
