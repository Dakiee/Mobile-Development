package com.example.lab6;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Weather;


public class WeekFragment extends Fragment {

    DecimalFormat df = new DecimalFormat("#.##");
    View view;
    RecyclerView recyclerView;
    ArrayList<Weather> weather_data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_week, container, false);
        recyclerView = view.findViewById(R.id.recycle_week);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String url = "https://api.openweathermap.org/data/2.5/onecall";
        String appId = "e53301e27efa0b66d05045d91b2742d3";
        String tmp = url + "?lat=46" +"&lon=105" + "&exclude=dialy" + "&appid=" + appId;

        new WeekSync().execute(tmp);

        return view;
    }
    private class WeekSync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String response = "";

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                InputStream input = new BufferedInputStream(conn.getInputStream());
                response = convertStreamToString(input);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray jsonArray = jsonResponse.getJSONArray("daily");
                JSONObject jsonHourly1 = jsonArray.getJSONObject(0);
                JSONObject jsonTemp = jsonHourly1.getJSONObject("temp");
                double temp_value1 = jsonTemp.getDouble("day") - 273.15;
                String temp_string1 = df.format(temp_value1);
                temp_value1 = Double.parseDouble(String.valueOf(temp_string1));

                JSONObject jsonHourly2 = jsonArray.getJSONObject(1);
                JSONObject jsonTemp1 = jsonHourly2.getJSONObject("temp");
                double temp_value2 = jsonTemp1.getDouble("day") - 273.15;
                String temp_string2 = df.format(temp_value2);
                temp_value2 = Double.parseDouble(String.valueOf(temp_string2));

                JSONObject jsonHourly3 = jsonArray.getJSONObject(2);
                JSONObject jsonTemp2 = jsonHourly3.getJSONObject("temp");
                double temp_value3 = jsonTemp2.getDouble("day") - 273.15;
                String temp_string3 = df.format(temp_value3);
                temp_value3 = Double.parseDouble(String.valueOf(temp_string3));

                JSONObject jsonHourly4 = jsonArray.getJSONObject(3);
                JSONObject jsonTemp3 = jsonHourly4.getJSONObject("temp");
                double temp_value4 = jsonTemp3.getDouble("day") - 273.15;
                String temp_string4 = df.format(temp_value4);
                temp_value4 = Double.parseDouble(String.valueOf(temp_string4));

                JSONObject jsonHourly5 = jsonArray.getJSONObject(4);
                JSONObject jsonTemp4 = jsonHourly5.getJSONObject("temp");
                double temp_value5 = jsonTemp4.getDouble("day") - 273.15;
                String temp_string5 = df.format(temp_value5);
                temp_value5 = Double.parseDouble(String.valueOf(temp_string5));

                JSONObject jsonHourly6 = jsonArray.getJSONObject(5);
                JSONObject jsonTemp5 = jsonHourly6.getJSONObject("temp");
                double temp_value6 = jsonTemp5.getDouble("day") - 273.15;
                String temp_string6 = df.format(temp_value6);
                temp_value6 = Double.parseDouble(String.valueOf(temp_string6));

                JSONObject jsonHourly7 = jsonArray.getJSONObject(6);
                JSONObject jsonTemp6 = jsonHourly7.getJSONObject("temp");
                double temp_value7 = jsonTemp6.getDouble("day") - 273.15;
                String temp_string7 = df.format(temp_value7);
                temp_value7 = Double.parseDouble(String.valueOf(temp_string7));

                Weather weather1 = new Weather(R.drawable.sun, temp_value1, "1 хоног");
                weather_data.add(weather1);

                Weather weather2 = new Weather(R.drawable.wind, temp_value2, "2 хоног");
                weather_data.add(weather2);

                Weather weather3 = new Weather(R.drawable.sun, temp_value3, "3 хоног");
                weather_data.add(weather3);

                Weather weather4 = new Weather(R.drawable.sun, temp_value4, "4 хоног");
                weather_data.add(weather4);

                Weather weather5 = new Weather(R.drawable.wind, temp_value5, "5 хоног");
                weather_data.add(weather5);

                Weather weather6 = new Weather(R.drawable.wind, temp_value6, "6 хоног");
                weather_data.add(weather6);

                Weather weather7 = new Weather(R.drawable.sun, temp_value7, "7 хоног");
                weather_data.add(weather7);
                recyclerView.setAdapter(new DayAdapter(weather_data));

                recyclerView.setAdapter(new DayAdapter(weather_data));

            }catch (JSONException e){
                e.printStackTrace();
            }
            Log.d("Response: ", response);
        }

        private String convertStreamToString(InputStream input) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return sb.toString();
        }
    }
}


