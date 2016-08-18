package com.example.abhi.quizapp;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
/**
 * Created by Abhi on 16-08-2016.
 * Custom listener for OnClick events of True and False button.
 */
class MyAnswerListener implements View.OnClickListener {
    private final String givenAnswer; //answer given by user
    private final Quiz quiz; //current quiz
    private final Context context; //applications context

    /**
     * Check whether the answer is correct or not and generate the appropriate toast
     * @param view - the View(currently True and False Buttons) which called this listener
     */
    @Override
    public void onClick(View view) {
        if(quiz.checkAnswer(givenAnswer)){
            Toast.makeText(context,"Correct Answer", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Wrong Answer", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Constructor for the listener
     * @param answer - The answer associated with the button
     * @param quiz - current quiz object
     * @param con - applications context required for toasts
     */
    MyAnswerListener(String answer, Quiz quiz, Context con){
        givenAnswer = answer;
        this.quiz = quiz;
        context = con;
    }
}
