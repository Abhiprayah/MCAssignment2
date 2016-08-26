package com.example.abhi.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {
    //tags for intent
    private static final String  QUESTION_ASKED = "com.example.abhi.quizapp.QUESTION_ASKED";
    private static final String HINT_TAKEN_STATUS = "com.example.abhi.quizapp.HINT_TAKEN_STATUS";

    //tags for saving in bundle
    private static final String NUMBER = "NUMBER";
    private static final String HINT_TAKEN = "HINT_TAKEN";
    private static final String RESULT = "RESULT";

    private TextView hint;
    private int number;
    private boolean hintTaken = false;
    private String result = "";

    private final View.OnClickListener hintButtonListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            hintTaken = true;
            int sqroot = (int)(Math.sqrt(number));
            result = "Look for divisors below " + sqroot + " for " + number;
            hint.setText(result);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        Button hintButton;
        hintButton = (Button)findViewById(R.id.hintButton);
        hintButton.setOnClickListener(hintButtonListener);
        hint = (TextView)findViewById(R.id.hintTextView);
        if(savedInstanceState == null) {
            Intent calledIntent = getIntent();
            number = calledIntent.getIntExtra(QUESTION_ASKED, 1000);
        }
        else{
            number = savedInstanceState.getInt(NUMBER);
            result = savedInstanceState.getString(RESULT);
            hint.setText(result);
            hintTaken = savedInstanceState.getBoolean(HINT_TAKEN);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(NUMBER, number);
        savedInstanceState.putBoolean(HINT_TAKEN, hintTaken);
        savedInstanceState.putString(RESULT, result);
    }

    //return an intent when back button is pressed
    @Override
    public void onBackPressed(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra(HINT_TAKEN_STATUS, hintTaken); //tell whether the user has taken int or not
        setResult(Activity.RESULT_OK, returnIntent); //set result to OK
        finish(); //return the intent
        super.onBackPressed();
    }
}
