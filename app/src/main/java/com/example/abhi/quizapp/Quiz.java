package com.example.abhi.quizapp;

import java.io.Serializable;

/**
 * Created by Abhi on 14-08-2016.
 */
public interface Quiz{
    void generateQuestion();
    boolean checkAnswer(String ans);
    String getQuestion();
}
