package com.example.android.projeto4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        LinearLayout play = (LinearLayout) findViewById(R.id.btnPlay);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playIntent = new Intent(PlayActivity.this, PlayActivity.class);
                startActivity(playIntent);
            }
        });

        LinearLayout playlist = (LinearLayout) findViewById(R.id.btnPlaylist);
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playlistIntent = new Intent(PlayActivity.this, PlaylistsActivity.class);
                startActivity(playlistIntent);
            }
        });

        LinearLayout settings = (LinearLayout) findViewById(R.id.btnSettings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(PlayActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });

        LinearLayout premium = (LinearLayout) findViewById(R.id.btnPremium);
        premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent premiumIntent = new Intent(PlayActivity.this, PremiumActivity.class);
                startActivity(premiumIntent);
            }
        });
    }
}
