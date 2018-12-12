package com.example.sjoerd.trivia;

import android.support.annotation.NonNull;

public class Highscore implements Comparable<Highscore> {

    private String name, score;

    public Highscore(String name, String score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(@NonNull Highscore other) {
        return score.compareTo(other.score);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
