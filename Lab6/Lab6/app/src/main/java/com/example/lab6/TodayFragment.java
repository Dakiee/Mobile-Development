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

public class TodayFragment extends Fragment {

    DecimalFormat df = new DecimalFormat("#.##");

    Weather weather1;

    RecyclerView recyclerView;
    ArrayList<Weather> weather_data = new ArrayList<>();

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_today, container, false);
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String url = "https://api.openweathermap.org/data/2.5/onecall";
        String appId = "e53301e27efa0b66d05045d91b2742d3";
        String tmp = url + "?lat=46" +"&lon=105" + "&exclude=dialy" + "&appid=" + appId;

        new DaySync().execute(tmp);

        return view;
    }

    private class DaySync extends AsyncTask<String, Void, String> {

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
                JSONArray jsonArray = jsonResponse.getJSONArray("hourly");
                JSONObject jsonHourly1 = jsonArray.getJSONObject(0);
                double  temp_value1 = jsonHourly1.getDouble("temp") - 273.15;
                String temp_string1 = df.format(temp_value1);
                temp_value1 = Double.parseDouble(String.valueOf(temp_string1));

                JSONObject jsonHourly2 = jsonArray.getJSONObject(1);
                double  temp_value2 = jsonHourly2.getDouble("temp") - 273.15;
                String temp_string2 = df.format(temp_value2);
                temp_value2 = Double.parseDouble(String.valueOf(temp_string2));

                JSONObject jsonHourly3 = jsonArray.getJSONObject(2);
                double  temp_value3 = jsonHourly3.getDouble("temp") - 273.15;
                String temp_string3 = df.format(temp_value3);
                temp_value3 = Double.parseDouble(String.valueOf(temp_string3));

                weather1 = new Weather(R.drawable.sun, temp_value1, "1 цаг");
                weather_data.add(weather1);

                Weather weather2 = new Weather(R.drawable.wind, temp_value2, "2 цаг");
                weather_data.add(weather2);


                Weather weather3 = new Weather(R.drawable.sun, temp_value3, "3 цаг");
                weather_data.add(weather3);

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