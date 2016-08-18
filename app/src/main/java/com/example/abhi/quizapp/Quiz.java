package com.example.abhi.quizapp;

/**
 * Created by Abhi on 14-08-2016.
 * Quiz interface for providing necessary functions for various types of quizzes
 */
interface Quiz{
    void generateQuestion();
    boolean checkAnswer(String ans);
    String getQuestion();
    int getAttempts();
}
