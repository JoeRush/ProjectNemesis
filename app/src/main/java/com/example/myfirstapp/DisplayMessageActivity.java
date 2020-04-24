package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myfirstapp.databinding.ActivityDisplayMessageBinding;


public class DisplayMessageActivity extends AppCompatActivity {
    private ActivityDisplayMessageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();

        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = binding.textView;
        textView.setText(message);
    }
}
