package com.example.TCSS450GROUP1.ui.connections;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.TCSS450GROUP1.R;
import com.example.TCSS450GROUP1.databinding.FragmentContactBinding;
import com.example.TCSS450GROUP1.model.UserInfoViewModel;
import com.example.TCSS450GROUP1.ui.weather.WeatherViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Joseph Rushford
 */
public class ContactFragment extends Fragment {
    private UserInfoViewModel mUserModel;
    private ContactViewModel mContactViewModel;
    private String[] mContacts;
    private FragmentContactBinding binding;
    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProvider provider= new ViewModelProvider(getActivity());
        mContactViewModel = provider.get(ContactViewModel.class);
        mUserModel = provider.get(UserInfoViewModel.class);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentContactBinding.inflate(inflater);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContactViewModel.connect(mUserModel.getEmail(), mUserModel.getJWT());
        //listOfContacts();
        mContactViewModel.addResponseObserver(getViewLifecycleOwner(),
                this::observeContacts);

    }

    private void listOfContacts() {
        ArrayList<String> contacts = new ArrayList<String>();
        contacts.add("123metest");
        contacts.add("12metest");
        contacts.add("1metest");
        contacts.add("1etest");
        contacts.add("1et");
        contacts.add("asdHHsddfg@");
        contacts.add("abAcvf123!1@");
        contacts.add("wasted");
        contacts.add("testing");
        contacts.add("fornick");
        contacts.add("test1");
        contacts.add("test2");
        contacts.add("test3");
        contacts.add("caron");
        contacts.add("caronn2");
        StringBuilder builder = new StringBuilder();
        for(int j = 0; j < contacts.size(); j++) {
            builder.append(contacts.get(j) + "\n\n");
        }
        binding.contactNames.setText(builder.toString());
    }

    private void observeContacts(JSONObject response) {
        Log.i("user", response.toString());
        if (response.length() > 0) {
            if (response.has("code")) {
                try {
//                    binding.contactNames.setText(
//                            mUserModel.getEmail());
                    binding.contactNames.setError(
                            "Error Authenticating: " +
                                    response.getJSONObject("data").getString("message"));
                } catch (JSONException e) {
                    Log.e("JSON Parse Error", e.getMessage());
                }
            } else {
                try {
                    getContacts(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        } else {
            Log.d("JSON Response", "No Response");
        }
    }

    private void getContacts(JSONObject response) throws JSONException {
        Log.i("contacts", response.toString());
        ArrayList<String> contacts = new ArrayList<String>();

        JSONArray jsonUsers = response.getJSONArray("values");
        for(int i = 0; i < jsonUsers.length(); i++) {
            JSONObject user = (new JSONObject(jsonUsers.getString(i)));
            contacts.add(user.getString("username"));
        }
        StringBuilder builder = new StringBuilder();
        for(int j = 0; j < contacts.size(); j++) {
            builder.append(contacts.get(j) + "\n\n");
        }
        Log.i("contacts", builder.toString());
        binding.contactNames.setText(builder.toString());
    }
}

