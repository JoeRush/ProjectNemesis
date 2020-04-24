package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.myfirstapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    //String theState;
    private static final String TAGCREATE = "MyCreation";
    private static final String TAGSTART  = "MyStart";
    private static final String TAGRESUME = "MyResume";
    private static final String TAGPAUSE = "MyPause";
    private static final String TAGSTOP = "MyStop";
    private static final String TAGRESTART = "MyRestart";
    private static final String TAGDESTROY = "MyDestroy";
    private static final String BREAKP = "My BreakPoint";
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d(TAGCREATE, "My Creation");
    }
    @Override
    protected  void onStart() {
        super.onStart();
        Log.w(TAGSTART, "My START");
    }
    @Override
    protected void onResume() {

        super.onResume();
        Log.i(TAGRESUME, "My RESUME");
    }
    @Override
    protected void onPause() {

        super.onPause();
        Log.e(TAGPAUSE, "my PAUSE");
    }
    @Override
    protected void onStop() {

        super.onStop();
        Log.i(TAGSTOP, "my STOP");
    }
    @Override
    protected void onRestart() {

        super.onRestart();
        Log.i(TAGRESTART, "my RESTART");
    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        Log.v(TAGDESTROY, "my END");
    }
    /** called when the user taps the send button */
    public void sendMessage(View view) {

        Intent intent = new Intent(this, DisplayMessageActivity.class);
       //// EditText editText = (EditText) findViewById(R.id.editText2);
       // String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, binding.editText2.getText().toString());
        startActivity(intent);
    }
}
