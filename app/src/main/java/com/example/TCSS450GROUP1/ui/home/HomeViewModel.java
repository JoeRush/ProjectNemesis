package com.example.TCSS450GROUP1.ui.home;

import android.app.Application;
import android.location.Location;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.TCSS450GROUP1.databinding.FragmentContactBinding;
import com.example.TCSS450GROUP1.databinding.FragmentHomeBinding;
import com.example.TCSS450GROUP1.io.RequestQueueSingleton;
import com.example.TCSS450GROUP1.ui.chat.ChatMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HomeViewModel extends AndroidViewModel {
    private MutableLiveData<Location> mLocation;
    private MutableLiveData<JSONObject> mResponse;
    private String mEmail;
    public HomeViewModel(@NonNull Application application) {
        super(application);

        mLocation = new MediatorLiveData<>();
        mResponse = new MutableLiveData<>();
        mResponse.setValue(new JSONObject());

    }

    public void addLocationObserver(@NonNull LifecycleOwner owner,
                    @NonNull Observer<? super Location> observer) {
        mLocation.observe(owner, observer);
    }
    public void addResponseObserver(@NonNull LifecycleOwner owner,
                                    @NonNull Observer<? super JSONObject> observer) {
        mResponse.observe(owner, observer);
    }

    public void setLocation(final Location location) {
        if (!location.equals(mLocation.getValue())) {
            mLocation.setValue(location);
        }
    }

    public Location getCurrentLocation() {
        return new Location(mLocation.getValue());
    }


    public void connect(String lat, String longitude, final String jwt) {

            try {
                Log.i("Made it:", "HERE ONE");

                String url = "http://team1-database.herokuapp.com/weather/zipcode?98498";
                JSONObject body = new JSONObject();
                Log.i("Made it:", body.toString());
                Request request = new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        body,
                        mResponse::setValue,
                        this::handleError) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<>();
                        // add headers <key,value>
                        headers.put("Authorization", jwt);
                        return headers;
                    }
                };

                request.setRetryPolicy(new DefaultRetryPolicy(
                        10_000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                //Instantiate the RequestQueue and add the request to the queue
                RequestQueueSingleton.getInstance(getApplication().getApplicationContext())
                        .addToRequestQueue(request);

            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    private void handleError(VolleyError error) {
        if (Objects.isNull(error.networkResponse)) {
            try {
                mResponse.setValue(new JSONObject("{" +
                        "error:\"" + error.getMessage() +
                        "\"}"));
            } catch (JSONException e) {
                Log.e("JSON PARSE", "JSON Parse Error in handleError");
            }
        }
        else {
            String data = new String(error.networkResponse.data, Charset.defaultCharset());
            try {
                mResponse.setValue(new JSONObject("{" +
                        "code:" + error.networkResponse.statusCode +
                        ", data:" + data +
                        "}"));
            } catch (JSONException e) {
                Log.e("JSON PARSE", "JSON Parse Error in handleError");
            }
        }
    }
}
