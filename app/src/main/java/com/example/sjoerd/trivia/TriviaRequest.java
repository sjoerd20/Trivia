package com.example.sjoerd.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TriviaRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private ArrayList<Question> questions= new ArrayList<>();
    private int nQuestions = 10;

    Callback activity;

    public interface Callback {
        void gotQuestions(ArrayList<Question> questions);
        void gotQuestionsError(String message);
    }

    // constructor
    public TriviaRequest(Context c) {
        this.context = c;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotQuestionsError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray questionArray = response.getJSONArray("results");

            // store each question in a Question object
            for (int i = 0; i < questionArray.length(); i++) {
                JSONObject questionObject = questionArray.getJSONObject(i);
                String category = questionObject.getString("category");
                String questionType = questionObject.getString("type");
                String difficulty = questionObject.getString("difficulty");
                String question = questionObject.getString("question");
                String correctAnswer = questionObject.getString("correct_answer");

                // add all incorrect answers to an ArrayList
                ArrayList<String> incorrectAnswers = new ArrayList<>();
                JSONArray incorrectAnswersArray = questionObject.getJSONArray("incorrect_answers");
                for (int j = 0; j < incorrectAnswersArray.length(); j++) {
                    incorrectAnswers.add(incorrectAnswersArray.getString(j));
                }

                // create a new Question object to store retrieved values
                Question retrievedQuestion = new Question(category, difficulty, question,
                                                          questionType, correctAnswer,
                                                          incorrectAnswers);
                questions.add(retrievedQuestion);
            }
        }
        catch(JSONException error) {
            Log.e("requestError", error.getMessage());
        }
        activity.gotQuestions(questions);
    }

    // retrieve questions from the Trivia API
    void getQuestions(Callback act) {
        this.activity = act;

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://opentdb.com/api.php?amount=" + nQuestions;

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
            queue.add(jsonObjectRequest);
        }
        catch(Exception error) {
            Log.e("requestError", error.getMessage());
        }
    }
}
