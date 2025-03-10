package com.winrush;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.LinkedList;

public class Wins extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wins);
        final SharedPreferences reader = getSharedPreferences("savefile", MODE_PRIVATE);
        
        updateRewardDisplay(reader, "simply", R.id.simplynumber);
        updateRewardDisplay(reader, "odeon", R.id.odeonnumber);
        updateRewardDisplay(reader, "mikel", R.id.mikelnumber);
        updateRewardDisplay(reader, "efood", R.id.efoodnumber);
        updateRewardDisplay(reader, "two", R.id.twonumber);
        updateRewardDisplay(reader, "fivehundred", R.id.fivehundrednumber);
        updateRewardDisplay(reader, "seven", R.id.sevennumber);
        updateRewardDisplay(reader, "dominos", R.id.dominosnumber);
        updateRewardDisplay(reader, "bluestar", R.id.bluestarnumber);
    }

    private void updateRewardDisplay(SharedPreferences prefs, String key, int textViewId) {
        int count = prefs.getInt(key, 0);
        TextView textView = findViewById(textViewId);
        textView.setText(count + "x");
        if (count > 0) {
            textView.setEnabled(true);
        }
    }
}