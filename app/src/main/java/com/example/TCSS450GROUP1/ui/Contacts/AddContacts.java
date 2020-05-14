package com.example.TCSS450GROUP1.ui.Contacts;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.TCSS450GROUP1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddContacts extends AppCompatActivity {

    public AddContacts() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_contacts);
    }

}
