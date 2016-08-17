package com.example.abhi.quizapp;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;
import android.view.View;
/**
 * Created by Abhi on 16-08-2016.
 */
public class MyAnswerListener implements View.OnClickListener {
    private final String givenAnswer;
    private final Quiz quiz;
    private final Context context;
    @Override
    public void onClick(View view) {
        if(quiz.checkAnswer(givenAnswer)){
            Toast.makeText(context,"Correct Answer", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Wrong Answer", Toast.LENGTH_SHORT).show();
        }
    }
    MyAnswerListener(String answer, Quiz q, Context con){
        givenAnswer = answer;
        this.quiz = q;
        context = con;
    }
}
