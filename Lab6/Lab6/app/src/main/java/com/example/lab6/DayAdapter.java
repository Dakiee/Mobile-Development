package com.example.lab6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.Weather;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.MyViewHolder> {

    ArrayList<Weather> weather_data;

    public DayAdapter(ArrayList<Weather> weather_data) {
        this.weather_data = weather_data;
    }

    @NonNull
    @Override
    public DayAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayAdapter.MyViewHolder holder, int position) {

        holder.img1.setImageResource(weather_data.get(position).getImg());
        holder.temp.setText(String.valueOf(weather_data.get(position).getTemp()));
        holder.day.setText(weather_data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return weather_data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img1;
        TextView temp, day, state;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            temp = itemView.findViewById(R.id.weather_temp);
            day = itemView.findViewById(R.id.weather_day);
            state = itemView.findViewById(R.id.weather_state);
        }
    }
}
