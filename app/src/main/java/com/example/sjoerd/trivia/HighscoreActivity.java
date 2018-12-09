package com.example.sjoerd.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HighscoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        // receive intent
        Intent intent = getIntent();
        int retrievedScore = intent.getIntExtra("score", 0);

        TextView scoreView = findViewById(R.id.scoreTextView);
        scoreView.setText(String.valueOf(retrievedScore));
    }

    // if Back is pressed, return to MenuActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HighscoreActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
