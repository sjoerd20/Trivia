package com.example.sjoerd.trivia;

public class Question {

    String Category, Difficulty, question, questionType, correct_answer, incorrect_answers;

    public Question(String category, String difficulty, String question, String questionType, String correct_answer, String incorrect_answers) {
        Category = category;
        Difficulty = difficulty;
        this.question = question;
        this.questionType = questionType;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }

    // getters and setters
    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(String difficulty) {
        Difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(String incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }
}
