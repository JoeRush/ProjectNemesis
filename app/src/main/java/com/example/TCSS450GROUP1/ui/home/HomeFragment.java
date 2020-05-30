package com.example.TCSS450GROUP1.ui.home;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.TCSS450GROUP1.R;
import com.example.TCSS450GROUP1.databinding.FragmentHomeBinding;
import com.example.TCSS450GROUP1.databinding.FragmentWeatherBinding;
import com.example.TCSS450GROUP1.model.NewMessageCountViewModel;
import com.example.TCSS450GROUP1.model.UserInfoViewModel;
import com.example.TCSS450GROUP1.ui.chat.ChatMessage;
import com.example.TCSS450GROUP1.ui.chat.ChatViewModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.badge.BadgeDrawable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    private NewMessageCountViewModel mNewMessageModel;
    private static final int HARD_CODED_CHAT_ID = 1;
    private ChatViewModel mChatModel;
    private UserInfoViewModel mUserModel;
    private HomeViewModel mModel;
    private HomeRecentViewModel mRecentModel;
    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ViewModelProvider provider= new ViewModelProvider(getActivity());
        mUserModel = provider.get(UserInfoViewModel.class);
        mNewMessageModel = provider.get(NewMessageCountViewModel.class);
        mModel = provider.get(HomeViewModel.class);
        mRecentModel = provider.get(HomeRecentViewModel.class);
        mRecentModel.setEmail(mUserModel.getEmail());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentHomeBinding binding = FragmentHomeBinding.bind(getView());

        binding.textEmailHome.setText(mUserModel.getEmail());

        mModel.connect("temp", "temp", mUserModel.getJWT());
        mModel.addResponseObserver(getViewLifecycleOwner(), this::observeCurrentWeather);
        mModel.addLocationObserver(getViewLifecycleOwner(), this::observeCurrentLocation);

//        binding.recentChatHome.setText(mModel.getRecentChat().getMessage());
//        mNewMessageModel.addMessageCountObserver(this, count -> {
//        mRecentModel.addChatObserver(getViewLifecycleOwner(), message-> {
//            binding.recentChatHome.setText(message.getMessage());
//        });
//        mChatModel.addMessageObserver(HARD_CODED_CHAT_ID, getViewLifecycleOwner(),
//                list -> {
//                    binding.recentChatHome.setText(list.get(list.size()-1).getMessage());
//                });
        //    binding.recentChatHome.setText("Number of unread messages are: ");
                    //+ mRecentModel.getRecentChat().getMessage());
//        });

        //mModel.addChatObserver(getViewLifecycleOwner(),
              //  this::observeRecentChat);

    }

    private void observeCurrentLocation(Location location) {
        String latitude = String.valueOf(mModel.getCurrentLocation().getLatitude());
        String longitude = String.valueOf(mModel.getCurrentLocation().getLongitude());
        binding.weatherHome.setText(latitude);
        binding.weatherTypeHome.setText(longitude);
    }

    private void observeCurrentWeather(JSONObject response) {
//        String latitude = String.valueOf(location.getLatitude());
//        String longitude = String.valueOf(location.getLongitude());
//        binding.weatherHome.setText(latitude);
//        binding.weatherTypeHome.setText(longitude);
        if (response.length() > 0) {
            if (response.has("code")) {
                try {
                    binding.weatherHome.setError(
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

    private void getCurrentWeather(JSONObject response) throws JSONException {
//        ArrayList<Integer> temps = new ArrayList<Integer>();
//        ArrayList<String> descriptions = new ArrayList<String>();
//        JSONObject jsonMessage = new JSONObject(response.getString("hourly"));
////        Log.i("DAILY", jsonMessage.getString("daily"));
//        JSONArray jsonDaily = jsonMessage.getJSONArray("temp");
//        for(int i = 0; i < 5; i++) {
//            JSONObject jsonDay = new JSONObject(jsonDaily.getString(i));
//            JSONObject jsonTemp = new JSONObject(jsonDay.getString("temp"));
//            Float kelvin = Float.parseFloat(jsonTemp.getString("day"));
//            int temperature = (int)kelvinToFar(kelvin);
//            temps.add(temperature);
//        }
    }



    public long kelvinToFar(Float theInt) {
        //If 9.0 and 5.0 not stated as doubles then they will use int division and result in 1
        double temp = (theInt * (9.0/5.0)) - 459.67;
        return Math.round(temp);
    }


}
