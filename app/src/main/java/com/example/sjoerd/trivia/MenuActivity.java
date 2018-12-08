package com.example.sjoerd.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    // add startButton onClick function
    public void onStartClicked(View view) {

        // start GamePlayActivity
        Intent intent = new Intent(MenuActivity.this, GamePlayActivity.class);
        startActivity(intent);
    }
}
