package com.winrush;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity {

    public void howtoplayscreen(View view) {
        ImageView howto = findViewById(R.id.howtoplay);
        if (!howto.isEnabled()) {

            ImageView playbutton = findViewById(R.id.playButton);
            ImageView shopbutton = findViewById(R.id.shopButton);
            ImageView winbutton = findViewById(R.id.winsButton);
            ImageView infobutton = findViewById(R.id.infoButton);
            playbutton.setEnabled(false);
            shopbutton.setEnabled(false);
            winbutton.setEnabled(false);
            infobutton.setEnabled(false);

            howto.setVisibility(View.VISIBLE);
            howto.setEnabled(true);

        } else {
            howto.setVisibility(View.INVISIBLE);
            howto.setEnabled(false);

            ImageView playbutton = findViewById(R.id.playButton);
            ImageView shopbutton = findViewById(R.id.shopButton);
            ImageView winbutton = findViewById(R.id.winsButton);
            ImageView infobutton = findViewById(R.id.infoButton);
            playbutton.setEnabled(true);
            shopbutton.setEnabled(true);
            winbutton.setEnabled(true);
            infobutton.setEnabled(true);
        }
    }

    public void gotoGame(View view) {
        Intent i = new Intent(Home.this, Game.class);
        startActivity(i);
    }

    public void gotoWins(View view) {
        Intent i = new Intent(Home.this, Wins.class);
        startActivity(i);
    }

    public void gotoInfo(View view) {
        Intent i = new Intent(Home.this, Info.class);
        startActivity(i);
    }

    public void gotoShop(View view) {
        Intent i = new Intent(Home.this, Gifts.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView howto = findViewById(R.id.howtoplay);
        howto.setVisibility(View.INVISIBLE);
        howto.setEnabled(false);
    }
}