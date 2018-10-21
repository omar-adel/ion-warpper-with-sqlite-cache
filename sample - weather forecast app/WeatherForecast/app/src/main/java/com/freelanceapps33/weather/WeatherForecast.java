package com.freelanceapps33.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import com.freelanceapps33.weather.adapters.WeatherAdapter;
import com.freelanceapps33.weather.models.Weather;
import com.freelanceapps33.weather.utilities.IonWrapper;
import com.koushikdutta.async.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherForecast extends AppCompatActivity implements IonWrapper.WebServiceInterFace {

    private IonWrapper ionWrapper;
    String weatherURL = "http://api.wunderground.com/api/838ed9367e8876bf/forecast/q/EG/Cairo.json";
    ListView lvItems;
    WeatherAdapter weatherAdapter;
    ArrayList<Weather> weatherArrayList = new ArrayList<Weather>();
    Weather weather = new Weather();

    ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        init();
    }

    public void init() {
        lvItems = (ListView) findViewById(R.id.lvItems);
        weatherArrayList = new ArrayList<Weather>();
        weatherAdapter = new WeatherAdapter(WeatherForecast.this, weatherArrayList);
        ionWrapper = new IonWrapper(this, this);
        call_webservice();

    }

    public void call_webservice() {
        params = new ArrayList<NameValuePair>();
        ionWrapper.getResponse("GET", weatherURL, params);
    }

    @Override
    public void parseString(String response) {

        try {
            JSONObject responseObject = new JSONObject(response);

            weatherArrayList.clear();
            JSONObject forecast = responseObject.getJSONObject("forecast");
            JSONObject simpleforecast = forecast.getJSONObject("simpleforecast");
            JSONArray forecastday = simpleforecast.getJSONArray("forecastday");

            for (int i = 0; i < forecastday.length(); i++) {
                JSONObject jObject = forecastday.getJSONObject(i);

                JSONObject date = jObject.getJSONObject("date");
                JSONObject high = jObject.getJSONObject("high");
                JSONObject low = jObject.getJSONObject("low");

                weather = new Weather();

                weather.setWeekday(date.getString("weekday"));
                weather.setMonthname(date.getString("monthname"));
                weather.setYear(date.getString("year"));
                weather.setDay(date.getString("day"));
                weather.setHigh_celsius(high.getString("celsius"));
                weather.setLow_celsius(low.getString("celsius"));
                weather.setIcon_url(jObject.getString("icon_url"));

                weatherArrayList.add(weather);
            }

            weatherAdapter = new WeatherAdapter(WeatherForecast.this, weatherArrayList);
            lvItems.setAdapter(weatherAdapter);

        } catch (Exception e) {

            Toast.makeText(WeatherForecast.this, getString(R.string.error), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void failResponse() {
        Toast.makeText(WeatherForecast.this, getString(R.string.error), Toast.LENGTH_LONG).show();
    }

}
