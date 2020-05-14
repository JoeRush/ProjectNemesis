package com.example.TCSS450GROUP1.ui.Contacts;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.TCSS450GROUP1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Contacts extends AppCompatActivity {

    private Button button;

    public Contacts() {
        // Required empty public constructor
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_contacts, container, false);
//
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contacts);
        button = findViewById(R.id.add_contacts);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddContacts1();
            }
        });
    }


    public void AddContacts1(){
        Intent intent = new Intent(this, AddContacts.class);
        startActivity(intent);
    }

}
