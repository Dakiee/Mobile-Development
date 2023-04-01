package com.example.recyclerviews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeClickListener {

    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeList = new ArrayList<>();
        recipeList.add(new Recipe("Recipe 1", "Description 1"));
        recipeList.add(new Recipe("Recipe 2", "Description 2"));
        recipeList.add(new Recipe("Recipe 3", "Description 3"));
        recipeList.add(new Recipe("Recipe 4", "Description 4"));
        recipeList.add(new Recipe("Recipe 5", "Description 5"));

        recyclerView = findViewById(R.id.recipe_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeAdapter = new RecipeAdapter(recipeList, this);
        recyclerView.setAdapter(recipeAdapter);
    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("recipe_name", recipe.getName());
        intent.putExtra("recipe_description", recipe.getDescription());
        startActivity(intent);
    }
}

