package com.example.TCSS450GROUP1.ui.weather;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.TCSS450GROUP1.R;
import com.example.TCSS450GROUP1.databinding.FragmentWeatherBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author Matthew Molina
 * The fragment holding the information on the weather
 */
public class WeatherFragment extends Fragment {

    private  WeatherViewModel mWeatherModelCurrent;
    private  WeatherViewModel mWeatherModelMultiple;
    private FragmentWeatherBinding binding;

    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeatherModelCurrent = new ViewModelProvider(getActivity()).get(WeatherViewModel.class);
        mWeatherModelMultiple = new ViewModelProvider(getActivity()).get(WeatherViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWeatherBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWeatherModelCurrent.connectToWeatherCurrent();
        mWeatherModelMultiple.connectToWeatherMultiple();
        mWeatherModelCurrent.addResponseObserver(getViewLifecycleOwner(),
                this::observeWeatherCurrent);
        mWeatherModelMultiple.addResponseObserver(getViewLifecycleOwner(),
                this::observeWeatherMultiple);
    }

    /**
     * @author Matthew Molina
     * Gets the weather information for the current conditions.
     * @param response the json
     */
    private void observeWeatherCurrent(JSONObject response) {
        if (response.length() > 0) {
            if (response.has("code")) {
                try {
                    binding.temp.setError(
                            "Error Authenticating: " +
                                    response.getJSONObject("data").getString("message"));
                } catch (JSONException e) {
                    Log.e("JSON Parse Error", e.getMessage());
                }
            } else {
                try {
                    getCurrentWeather(response);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Log.d("JSON Response", "No Response");
        }
    }

    /**
     * Gets the weather conditions for both the 5 day and hourly conditions.
     * @param response the json
     */
    private void observeWeatherMultiple(JSONObject response) {
        if (response.length() > 0) {
            if (response.has("code")) {
                try {
                    binding.temp.setError(
                            "Error Authenticating: " +
                                    response.getJSONObject("data").getString("message"));
                } catch (JSONException e) {
                    Log.e("JSON Parse Error", e.getMessage());
                }
            } else {
                try {
                    getMultipleWeather(response);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Log.d("JSON Response", "No Response");
        }
    }

    /**
     * Gets the current weather and formats it.
     * @param response the json
     * @throws JSONException json
     */
    public void getCurrentWeather(JSONObject response) throws JSONException{
        //To get the message json
        JSONObject jsonMessage = new JSONObject(response.getString("message"));
        Log.i("Json", jsonMessage.toString());

        //binding.cityName.setText(response.getString("message"));
        binding.cityName.setText(jsonMessage.getString("name"));

        //to get the main json, then the temp
        JSONObject jsonMain = new JSONObject(jsonMessage.getString("main"));
        Float kelvin = Float.parseFloat(jsonMain.getString("temp"));
        //Log.i("json", jsonMessage.toString());
        int temperature = (int)kelvinToFar(kelvin);
        binding.temp.setText(String.valueOf(temperature + "°"));

        //gets the array held in the 'weather' section
        JSONArray jsonWeatherArray = new JSONArray(jsonMessage
                .getString("weather"));

        //converts the above array into a string to be cast into JSONObject
        String str = jsonWeatherArray.getString(0);

        //Casts into JSONObject and extract the description for the fragment
        String jsonWeather = new JSONObject(str).getString("description");
        binding.weatherType.setText(jsonWeather);
    }

    /**
     * Gets the 5 day and hourly forecasts and formats it.
     * @param response the json
     * @throws JSONException json
     */
    public void getMultipleWeather(JSONObject response) throws JSONException{
        ArrayList<Integer> temps = new ArrayList<Integer>();
        ArrayList<String> descriptions = new ArrayList<String>();
        JSONObject jsonMessage = new JSONObject(response.getString("message"));
        Log.i("DAILY", jsonMessage.getString("daily"));
        JSONArray jsonDaily = jsonMessage.getJSONArray("daily");
        for(int i = 0; i < 5; i++) {
            JSONObject jsonDay = new JSONObject(jsonDaily.getString(i));
            JSONObject jsonTemp = new JSONObject(jsonDay.getString("temp"));
            Float kelvin = Float.parseFloat(jsonTemp.getString("day"));
            int temperature = (int)kelvinToFar(kelvin);
            temps.add(temperature);
        }

        for(int i = 0; i < 5; i++) {
            JSONObject jsonDay = new JSONObject(jsonDaily.getString(i));
            JSONArray jsonWeather = new JSONArray(jsonDay.getString("weather"));
            JSONObject jsonInsideWeather = new JSONObject(jsonWeather.getString(0));
            String desc = jsonInsideWeather.getString("description");

            descriptions.add(desc);
        }

        Log.i("TEMPS", temps.toString());
        Log.i("DESCS", descriptions.toString());

        DateFormat df = new SimpleDateFormat("EE MMM dd:   ");
        Calendar cal = Calendar.getInstance();
        //String tod = df.format(today);

        //Manually add the 5 day forecast
        binding.dayOneWeather.setText(df.format(cal.getTime()) + temps.get(0) + "°, "
                + descriptions.get(0));

        cal.add(Calendar.DAY_OF_YEAR, 1);
        binding.dayTwoWeather.setText(df.format(cal.getTime()) + temps.get(1) + "°, "
                + descriptions.get(1));

        cal.add(Calendar.DAY_OF_YEAR, 1);
        binding.dayThreeWeather.setText(df.format(cal.getTime()) + temps.get(2) + "°, "
                + descriptions.get(2));

        cal.add(Calendar.DAY_OF_YEAR, 1);
        binding.dayFourWeather.setText(df.format(cal.getTime()) + temps.get(3) + "°, "
                + descriptions.get(3));

        cal.add(Calendar.DAY_OF_YEAR, 1);
        binding.dayFiveWeather.setText(df.format(cal.getTime()) + temps.get(4) + "°, "
                + descriptions.get(4));

        //HOURLY
        ArrayList<Integer> hourTemps = new ArrayList<Integer>();
        ArrayList<String> unixTime = new ArrayList<String>();
        ArrayList<String> hourDescriptions = new ArrayList<String>();
        JSONArray jsonHourly = jsonMessage.getJSONArray("hourly");

        //The temps
        for(int i = 0; i < 5; i++) {
            JSONObject jsonInnerHourly = new JSONObject(jsonHourly.getString(i));
            //get unix time
            unixTime.add(jsonInnerHourly.getString("dt"));
            //rest
            String thisTemp = jsonInnerHourly.getString("temp");
            Float kelvin = Float.parseFloat(thisTemp);
            int temperature = (int)kelvinToFar(kelvin);
            hourTemps.add(temperature);
        }
        Log.i("HOURLY TEMPS", hourTemps.toString());

        //The Descriptions
        for(int i = 0; i < 5; i++) {
            JSONObject jsonInnerHourly = new JSONObject(jsonHourly.getString(i));
            JSONArray descArr = new JSONArray(jsonInnerHourly.getString("weather"));
            JSONObject hourWeather = new JSONObject(descArr.getString(0));
            hourDescriptions.add(hourWeather.getString("description"));
        }
        Log.i("HOUR DESCRIPTIONS", hourDescriptions.toString());
        Log.i("UNIX TIMES", unixTime.toString());
        Date expiry = new Date(Long.parseLong(unixTime.get(0)) * 1000);
        String exp = expiry.toString();
        //Log.i("UNIX TIME!!!!!", exp);
        DateFormat secondFormat = new SimpleDateFormat("HH:mm:ss -    ");
        Log.i("UNIX TIME!!!!!", secondFormat.format(expiry));

        //time of day in hour:min:sec
        Date hour1 = new Date(Long.parseLong(unixTime.get(0)) * 1000);
        Date hour2 = new Date(Long.parseLong(unixTime.get(1)) * 1000);
        Date hour3 = new Date(Long.parseLong(unixTime.get(2)) * 1000);
        Date hour4 = new Date(Long.parseLong(unixTime.get(3)) * 1000);
        Date hour5 = new Date(Long.parseLong(unixTime.get(4)) * 1000);

        binding.hourOne.setText(secondFormat.format(hour1) + hourTemps.get(0) + "°, "
                + hourDescriptions.get(0));
        binding.hourTwo.setText(secondFormat.format(hour2) + hourTemps.get(1) + "°, "
                + hourDescriptions.get(1));
        binding.hourThree.setText(secondFormat.format(hour3) + hourTemps.get(2) + "°, "
                + hourDescriptions.get(2));
        binding.hourFour.setText(secondFormat.format(hour4) + hourTemps.get(3) + "°, "
                + hourDescriptions.get(3));
        binding.hourFive.setText(secondFormat.format(hour5) + hourTemps.get(4) + "°, "
                + hourDescriptions.get(4));


    }

    /**
     * Converts Kelvin to Farenheit
     * @param theInt
     * @return
     */
    public long kelvinToFar(Float theInt) {
        //If 9.0 and 5.0 not stated as doubles then they will use int division and result in 1
        double temp = (theInt * (9.0/5.0)) - 459.67;
        return Math.round(temp);
    }
}
