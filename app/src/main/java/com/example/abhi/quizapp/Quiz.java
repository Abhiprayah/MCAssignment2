package com.example.abhi.quizapp;

/**
 * Created by Abhi on 14-08-2016.
 */
interface Quiz{
    void generateQuestion();
    boolean checkAnswer(String ans);
    String getQuestion();
}
