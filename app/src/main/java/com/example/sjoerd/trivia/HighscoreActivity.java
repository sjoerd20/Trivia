package com.example.sjoerd.trivia;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class HighscoreActivity extends AppCompatActivity implements HighscoreRequest.CallbackHighscore, Response.Listener, Response.ErrorListener {

    private int retrievedScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        // receive intent
        Intent intent = getIntent();
        retrievedScore = intent.getIntExtra("score", 0);

        TextView scoreView = findViewById(R.id.scoreTextView);
        scoreView.setText("Your score: " + String.valueOf(retrievedScore));
    }

    public void onSendClicked(View v) {
        // retrieve name
        TextInputEditText nameEdit = findViewById(R.id.nameInput);
        String name = "";
        try {
            name = nameEdit.getText().toString();
        }
        catch(Exception e) {
            Log.e("inputText", e.getMessage());
        }

        // set highscore to server
        String url = "https://ide50-s-terpstra.cs50.io/list";
        RequestQueue queue = Volley.newRequestQueue(this);
        PostRequest request = new PostRequest(Request.Method.POST, url, this, this);
        request.provideParams(name, retrievedScore);
        queue.add(request);

        // remove button and text input
        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setVisibility(View.GONE);
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        textInputLayout.setVisibility(View.GONE);
    }

    // error response listener for storing highscores
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    // response listener for storing highscores
    @Override
    public void onResponse(Object response) {

        // retrieve highscores
        HighscoreRequest highscoreRequest = new HighscoreRequest(this);
        highscoreRequest.getHighscores(this);
    }

    @Override
    public void gotHighscores(ArrayList<Highscore> highscores) {
        Toast.makeText(this, "succesful", Toast.LENGTH_LONG).show();

        // show highscores
        HighscoreAdapter adapter = new HighscoreAdapter(this, R.layout.highscore_item, highscores);
        ListView listView = findViewById(R.id._dynamic);
        listView.setAdapter(adapter);
    }

    @Override
    public void gotHighscoresError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // if Back is pressed, return to MenuActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HighscoreActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
