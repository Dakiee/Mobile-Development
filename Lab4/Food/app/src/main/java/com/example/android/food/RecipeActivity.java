package com.example.android.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity {

    String[] foodNameList = {"Гурил 250 гр\nУс 150 мл\nБөөрөнхий мах, гахайн мах,\nпепперони, тахианы мах,\nМоццарелла бяслаг\nДавс (Амталгаагаар)\nХар нунтаг чинжүү (Амталгаагаар)\nГоньдны толгой 1 ширхэг\nУргамлын тос 750 мл (шарахад хэрэглэнэ)",
            "Барбекью соусаар амталсан тахианы мах\nНогоон сонгино 30-35 гр\nСонгино 1 ш\nЧинжүү 150 гр\nДавс 1/2 ц/х\nХар перц 1/4ц/х\nУргамлын тос 25 гр\nТөмс 150 гр\nЛууван 60 гр\nшар манжин 150 гр\nЛаврын навч 1-2 ш",
            "Гурил 400 гр\n Шар лууван 150 гр\nБайцаа 200гр\nТөмс эсвэл улирлын чанартай дурын ногоонууд 200 гр\nХан боргоцой, Гахайн мах\nУргамлын тос 100 гр\nБөөрөнхий сонгино 1 ш\nСармис 1-2 хумс \nХар перец, лаврын навч гэх мэт хоол амтлагчууд," ,
            "Хонь болон үхрийн татсан мах 300гр\nГурил 150 гр\nСонгино 1 ш\nус, давс бага зэрэг"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Intent intent = getIntent();
        String message = intent.getStringExtra("ner");
        String hi = new String();
        switch (message){
            case "Маханд дурлагсад": {
                hi = foodNameList[0];
                break;
            }
            case "Хавайн": {
                hi = foodNameList[1];
                break;
            }
            case "Бяслагт": {
                hi = foodNameList[2];
                break;
            }
            case "Пепперони": {
                hi = foodNameList[3];
                break;
            }
            default:
                break;
        }
        TextView textView = findViewById(R.id.FoodMenu);
        textView.setText(hi);
    }
}