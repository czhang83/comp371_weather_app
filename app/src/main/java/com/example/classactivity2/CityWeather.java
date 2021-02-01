package com.example.classactivity2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CityWeather extends AppCompatActivity {


    private String location;
    private String high_temp;
    private String low_temp;
    private String feels_like;

    private TextView textView_location;
    private TextView textView_description;
    private TextView textView_high_temp;
    private TextView textView_low_temp;
    private TextView textView_feels_like;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);

        textView_location = findViewById(R.id.textView_location);
        textView_description = findViewById(R.id.textView_description);
        textView_high_temp = findViewById(R.id.textView_high_temp);
        textView_low_temp = findViewById(R.id.textView_low_temp);
        textView_feels_like = findViewById(R.id.textView_feels_like_description);

        Intent intent = getIntent();
        JSONObject receivedMessage = null;
        try {
            // unpack the string to json
            receivedMessage = new JSONObject(intent.getStringExtra("weather"));

            String country = receivedMessage.getJSONObject("sys").getString("country");
            String city = receivedMessage.getString("name");
            location = city + ", " + country;
            textView_location.setText(location);
            textView_description.setText(receivedMessage.getJSONArray("weather").getJSONObject(0).getString("description"));
            JSONObject main = receivedMessage.getJSONObject("main");

            // convert to celsius and only display one digit
            high_temp = String.format("%.1f", (Double.parseDouble(main.getString("temp_max")) - 273.15)) + " Celsius";
            low_temp = String.format("%.1f", (Double.parseDouble(main.getString("temp_min"))-273.15) )+ " Celsius";
            feels_like = String.format("%.1f", (Double.parseDouble(main.getString("feels_like")) - 273.15)) + " Celsius";
            textView_high_temp.setText(high_temp);
            textView_low_temp.setText(low_temp);
            textView_feels_like.setText(feels_like);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
