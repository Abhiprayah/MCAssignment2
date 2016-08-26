package com.example.abhi.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    //intent tags
    private static final String  QUESTION_ASKED = "com.example.abhi.quizapp.QUESTION_ASKED";
    private static final String CHEAT_STATUS = "com.example.abhi.quizapp.CHEAT_STATUS";
    //tags for saving in Bundle onSaveInstance
    private static final String NUMBER = "NUMBER";
    private static final String CHEATED = "CHEATED";
    private static final String RESULT = "RESULT";

    private TextView answer;
    private int number;
    private boolean hasCheated = false;
    private String result = "";

    private final View.OnClickListener cheatButtonListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            //user has cheated
            hasCheated = true;
            //show the answer
            if(isPrime(number)){
                result = number + " IS PRIME";
            }
            else result= number + " IS NOT PRIME";
            answer.setText(result);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Button cheatButton;
        cheatButton = (Button)findViewById(R.id.cheatButton);
        cheatButton.setOnClickListener(cheatButtonListener);
        answer = (TextView)findViewById(R.id.cheatTextView);

        if(savedInstanceState != null) {
            number = savedInstanceState.getInt(NUMBER);
            result = savedInstanceState.getString(RESULT);
            answer.setText(result);
            hasCheated = savedInstanceState.getBoolean(CHEATED);
        }
        else {
            final Intent calledIntent = this.getIntent();
            number = calledIntent.getIntExtra(QUESTION_ASKED, 0);
            answer.setText(result);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(NUMBER, number);
        savedInstanceState.putBoolean(CHEATED, hasCheated);
        savedInstanceState.putString(RESULT, result);
    }

    //utility function to check whether a number is prime
    private boolean isPrime(int num)
    {
        if(num == 1) return false;
        if(num == 2) return true;
        if((num % 2) == 0) return false;
        boolean isPrime = true;
        for(int i = 3; i <= (int)(Math.sqrt(num)) + 1; i += 2){
            if(num % i == 0){
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    @Override
    public void onBackPressed(){
        final Intent returnIntent = new Intent();
        returnIntent.putExtra(CHEAT_STATUS,hasCheated); //put whether the user has cheated or not
        setResult(Activity.RESULT_OK, returnIntent); //set result to OK
        finish(); //return the intent
        super.onBackPressed();
    }
}
