package com.example.sjoerd.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class GamePlayActivity extends AppCompatActivity implements TriviaRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        // request questions
        TriviaRequest triviaRequest = new TriviaRequest(this);
        triviaRequest.getQuestions(this);
    }

    @Override
    public void gotQuestions(ArrayList<Question> questions) {
        Toast.makeText(this, "Questions received!", Toast.LENGTH_LONG).show();


    }

    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
