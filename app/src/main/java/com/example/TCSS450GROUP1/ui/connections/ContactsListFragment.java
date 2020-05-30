package com.example.TCSS450GROUP1.ui.connections;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.TCSS450GROUP1.databinding.FragmentContactBinding;
import com.example.TCSS450GROUP1.databinding.FragmentContactsListBinding;
import com.example.TCSS450GROUP1.model.UserInfoViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Joseph Rushford
 */
public class ContactsListFragment extends Fragment {

    private ContactListViewModel mContactListViewModel;
    private ArrayList<String> mContacts;
    private FragmentContactsListBinding binding;
    public ContactsListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContacts = new ArrayList<>();
        mContactListViewModel = new ViewModelProvider(getActivity()).get(ContactListViewModel.class);
        UserInfoViewModel model = new ViewModelProvider(getActivity()).get(UserInfoViewModel.class);
        mContactListViewModel.connect(model.getEmail(), model.getJWT());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentContactsListBinding.inflate(inflater);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContactListViewModel.addContactListObserver(getViewLifecycleOwner(),
               contactsList -> {
                   if(!contactsList.isEmpty()) {
                        binding.contactlistCardRoot.setAdapter(new ContactListRecyclerViewAdapter(contactsList));
                   }
               });
       // binding.buttonAddContactlist.setOnClickListener(this::attemptToAdd);
        //binding.buttonDeleteContactlist.setOnClickListener(this::attemptToDelete);

    }

    private void attemptToAdd(View view) {
    }

    private void attemptToDelete(View view) {
    }

}

