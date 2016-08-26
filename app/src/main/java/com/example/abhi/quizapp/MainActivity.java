package com.example.abhi.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TRUE = "True";
    private static final String FALSE = "False";
    private static final String QUIZ = "Quiz";
    private static final String TAG = "QuizApp";
    private static final String SCORE = "Score";
    //Tags passed to intent
    private static final String  QUESTION_ASKED = "com.example.abhi.quizapp.QUESTION_ASKED";
    private static final String HINT_TAKEN_STATUS = "com.example.abhi.quizapp.HINT_TAKEN_STATUS";
    private static final String CHEAT_STATUS = "com.example.abhi.quizapp.CHEAT_STATUS";
    //return results
    private static final int REQUEST_RESULT_FROM_CHEAT_ACTIVITY = 100;
    private static final int REQUEST_RESULT_FROM_HINT_ACTIVITY = 101;
    //tags for extra information
    private static final String CHEATED = "CHEATED";
    private static final String HINT_TAKEN = "HINT_TAKEN";

    private static boolean cheated;
    private static boolean hint_taken;
    private Quiz quiz;
    private static int score = 0;   //Player's Score - Increments only if correct on first attempt.
    private static TextView questionTextView;
    private static TextView scoreTextView;

    //Listener for next button - generates a new question only if previous one has been attempted
    private final View.OnClickListener nextButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(quiz.getAttempts() == 0){
                Toast.makeText(getApplicationContext(),"Attempt the question first", Toast.LENGTH_SHORT).show();
                return;
            }
            //reset question and related state
            quiz.generateQuestion();
            cheated = false;
            hint_taken = false;
            questionTextView.setText(quiz.getQuestion());
        }
    };

    private final View.OnClickListener hintButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //create intent for hint activity
            final Intent hintIntent = new Intent(getApplicationContext(),HintActivity.class);
            hintIntent.putExtra(QUESTION_ASKED, quiz.questionAsked());
            startActivityForResult(hintIntent, REQUEST_RESULT_FROM_HINT_ACTIVITY);
        }
    };

    private final View.OnClickListener cheatButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //create intent for cheat activity
            final Intent cheatIntent = new Intent(getApplicationContext(),CheatActivity.class);
            cheatIntent.putExtra(QUESTION_ASKED, quiz.questionAsked());
            startActivityForResult(cheatIntent, REQUEST_RESULT_FROM_CHEAT_ACTIVITY);
        }
    };

    //method for updating the player's score
    static void updateScore(){
        if(!cheated && !hint_taken) score+=2; //if correct on first attempt without cheating and taking hint
        else if(!cheated) score+=1; //if correct on first attempt without cheating
        else score+=0;
        String temp = "Score : " + score;
        scoreTextView.setText(temp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = (TextView)findViewById(R.id.Question);
        //Generate question if first time else load question and score
        if(savedInstanceState == null){
            hint_taken = false;
            cheated = false;
            score = 0;
            quiz = new MathQuiz();
            quiz.generateQuestion();
        }
        else{
            quiz = (MathQuiz)(savedInstanceState.getSerializable(QUIZ));
            score = savedInstanceState.getInt(SCORE);
            hint_taken = savedInstanceState.getBoolean(HINT_TAKEN);
            cheated = savedInstanceState.getBoolean(CHEATED);
        }

        //get objects of required Textview's and set their text
        questionTextView.setText(quiz.getQuestion());
        scoreTextView = (TextView)findViewById(R.id.Score);
        String temp = "Score : " + score;
        scoreTextView.setText(temp);

        //get buttons and associate the appropriate listener with them
        Button trueButton;
        Button falseButton;
        Button nextButton;
        Button hintButton;
        Button cheatButton;
        trueButton = (Button)findViewById(R.id.trueButton);
        falseButton = (Button)findViewById(R.id.falseButton);
        nextButton = (Button)findViewById(R.id.nextButton);
        hintButton = (Button)findViewById(R.id.hintActivityButton);
        cheatButton = (Button)findViewById(R.id.cheatActivityButton);
        nextButton.setOnClickListener(nextButtonListener);
        trueButton.setOnClickListener(new MyAnswerListener(TRUE,quiz,getApplicationContext()));
        falseButton.setOnClickListener(new MyAnswerListener(FALSE,quiz,getApplicationContext()));
        hintButton.setOnClickListener(hintButtonListener);
        cheatButton.setOnClickListener(cheatButtonListener);

        Log.i(TAG, "Inside OnCreate");
    }

    //overridden methods for easier debugging
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "Inside OnSaveInstance");

        //save the required information
        savedInstanceState.putSerializable(QUIZ,(MathQuiz)quiz);
        savedInstanceState.putInt(SCORE,score);
        savedInstanceState.putBoolean(HINT_TAKEN, hint_taken);
        savedInstanceState.putBoolean(CHEATED, cheated);
    }

    //when return is made from an intent activity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        //check if user cheated
        if(requestCode == REQUEST_RESULT_FROM_CHEAT_ACTIVITY){
            if(resultCode == Activity.RESULT_OK){
                if(data.getBooleanExtra(CHEAT_STATUS, false)) cheated = true;
                Toast.makeText(getApplicationContext(),"You have cheated", Toast.LENGTH_SHORT).show();
            }
        }
        //check if user took hint
        else if(requestCode == REQUEST_RESULT_FROM_HINT_ACTIVITY){
            if(resultCode == Activity.RESULT_OK){
                if(data.getBooleanExtra(HINT_TAKEN_STATUS, false)) hint_taken = true;
                Toast.makeText(getApplicationContext(),"You have taken hint", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "Inside OnStart");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"Inside OnPause");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"Inside OnResume");

    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "Inside OnStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");
    }
}
