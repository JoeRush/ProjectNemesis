package com.example.TCSS450GROUP1.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TCSS450GROUP1.R;

public class AccountActivity extends AppCompatActivity {
    private SharedPreferences mSharedTheme;
    private static final String THEME_KEY = "currentTheme";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSharedTheme = getSharedPreferences("currentTheme", MODE_PRIVATE);
        mSharedTheme.getString(THEME_KEY, "default");
        if(mSharedTheme.getString(THEME_KEY, "default").equals("default")) {
            getTheme().applyStyle(R.style.AppTheme, true);
        } else {
            getTheme().applyStyle(R.style.OverlayThemePink, true);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
    }
}
