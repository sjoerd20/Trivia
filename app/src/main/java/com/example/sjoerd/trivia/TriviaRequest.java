package com.example.sjoerd.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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

        // TODO implement questions extraction
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
