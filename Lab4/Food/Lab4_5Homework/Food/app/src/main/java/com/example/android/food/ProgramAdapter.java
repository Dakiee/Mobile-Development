package com.example.android.food;

import static com.example.android.food.MainActivity.EXTRA_MESSAGE;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgramAdapter extends Adapter<ProgramAdapter.ViewHolder>  {
    Context context;
    String[] foodNameList;
    int[] images;
    LayoutInflater inflater;



    public ProgramAdapter(Context context, String[] foodNameList, int[] images ) {
        this.context = context;
        this.foodNameList = foodNameList;
        this.images = images;
        inflater = LayoutInflater.from(this.context);
    }
    @Override
    public ProgramAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view, this);
    }
    @Override
     public void onBindViewHolder(ProgramAdapter.ViewHolder holder, int position){
        holder.rowName.setText(foodNameList[position]);
        holder.rowImage.setImageResource(images[position]);
    }
    @Override
    public int getItemCount() {
        return foodNameList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView rowName;
        ImageView rowImage;

        final ProgramAdapter mAdapter;

        public ViewHolder( View itemView, ProgramAdapter adapter) {
            super(itemView);
            rowName = itemView.findViewById(R.id.textView1);
            rowImage = itemView.findViewById(R.id.imageView);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mAdapter.context, RecipeActivity.class);
            intent.putExtra("ner", foodNameList[getLayoutPosition()]);
            context.startActivity(intent);
        }
    }
}
