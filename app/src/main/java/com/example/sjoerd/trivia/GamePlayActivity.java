package com.example.sjoerd.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GamePlayActivity extends AppCompatActivity implements TriviaRequest.Callback {

    private int nQuestions = 10;
    private String typeOfQuestion = "boolean";
    private ArrayList<Question> questions;
    private Trivia trivia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        // request questions
        TriviaRequest triviaRequest = new TriviaRequest(this, nQuestions, typeOfQuestion);
        triviaRequest.getQuestions(this);
    }

    @Override
    public void gotQuestions(ArrayList<Question> questions) {
        Toast.makeText(this, "Questions received!", Toast.LENGTH_LONG).show();
        this.questions = questions;

        // set up listeners for True/False buttons
        Button trueButton = findViewById(R.id.trueButton);
        Button falseButton = findViewById(R.id.falseButton);
        trueButton.setOnClickListener(new TrueFalseButtonListener(true));
        falseButton.setOnClickListener(new TrueFalseButtonListener(false));

        // create a new trivia object to manage the game logic
        this.trivia = new Trivia(questions);

        // show first question
        showNextQuestion();
    }

    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // add a listener for True/False buttons
    private class TrueFalseButtonListener implements View.OnClickListener {

        private Boolean isButtonTrue;

        private TrueFalseButtonListener(Boolean isButtonTrue) {
            this.isButtonTrue = isButtonTrue;
        }

        @Override
        public void onClick(View v) {

            // get correct answer from current question
            String correctAnswer = trivia.getCurrentQuestion().getCorrectAnswer();
            if (isButtonTrue) {
                if (correctAnswer.equals("True")) {
                    Toast.makeText(getApplicationContext(), "True: well done!", Toast.LENGTH_LONG).show();
                    trivia.answeredSuccesfully();
                }
                else {
                    Toast.makeText(getApplicationContext(), "False: you failed!", Toast.LENGTH_LONG).show();
                }
            }
            else {
                if (correctAnswer.equals("False")) {
                    Toast.makeText(getApplicationContext(), "False: well done!", Toast.LENGTH_LONG).show();
                    trivia.answeredSuccesfully();
                }
                else {
                    Toast.makeText(getApplicationContext(), "True: you failed!", Toast.LENGTH_LONG).show();
                }
            }

            // go to next question
            showNextQuestion();
        }
    }


    // get next question from Trivia and update UI
    private void showNextQuestion() {
        Question currentQuestion = trivia.getNextQuestion();

        if (currentQuestion == null) {
            Toast.makeText(getApplicationContext(), "All questions answered", Toast.LENGTH_LONG).show();
            // end game
        }

        // update ui
        else {
            String question = currentQuestion.getQuestion();
            TextView questionView = findViewById(R.id.questionTextView);
            questionView.setText(question);
        }
    }
}
