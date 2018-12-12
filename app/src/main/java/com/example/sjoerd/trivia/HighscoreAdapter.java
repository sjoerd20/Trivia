package com.example.sjoerd.trivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighscoreAdapter extends ArrayAdapter<Highscore> {

    private ArrayList<Highscore> highscores;

    // constructor
    public HighscoreAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Highscore> objects) {
        super(context, resource, objects);
        this.highscores = objects;

        Collections.sort(highscores);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.highscore_item, parent,
                    false);
        }

        // get current highscore
        Highscore highscore = highscores.get(position);

        TextView nameView = convertView.findViewById(R.id.nameView);
        TextView scoreView = convertView.findViewById(R.id.scoreView);

        nameView.setText(highscore.getName());
        scoreView.setText(highscore.getScore());

        return convertView;
    }

}
