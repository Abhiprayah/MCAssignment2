package com.example.abhi.quizapp;

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
    private Quiz quiz;
    private static int score = 0;   //Player's Score - Increments only if correct on first attempt.
    private static TextView questionTextView;
    private static TextView scoreTextView;

    //Listener for next button - generates a new question only if previous one has been attempted
    private View.OnClickListener nextButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(quiz.getAttempts() == 0){
                Toast.makeText(getApplicationContext(),"Attempt the question first", Toast.LENGTH_SHORT).show();
                return;
            }
            quiz.generateQuestion();
            questionTextView.setText(quiz.getQuestion());
        }
    };

    //method for updating the player's score
    static void updateScore(){
        score+=1;
        String temp = "Score : " + score;
        scoreTextView.setText(temp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Generate question if first time else load question and score
        if(savedInstanceState == null){
            quiz = new MathQuiz();
            quiz.generateQuestion();
        }
        else{
            quiz = (MathQuiz)savedInstanceState.getSerializable(QUIZ);
            score = savedInstanceState.getInt(SCORE);
        }

        //get objects of required Textview's and set their text
        questionTextView = (TextView)findViewById(R.id.Question);
        questionTextView.setText(quiz.getQuestion());
        scoreTextView = (TextView)findViewById(R.id.Score);
        String temp = "Score : " + score;
        scoreTextView.setText(temp);

        //get buttons and associate the appropriate listener with them
        Button trueButton;
        Button falseButton;
        Button nextButton;
        trueButton = (Button)findViewById(R.id.trueButton);
        falseButton = (Button)findViewById(R.id.falseButton);
        nextButton = (Button)findViewById(R.id.nextButton);
        nextButton.setOnClickListener(nextButtonListener);
        trueButton.setOnClickListener(new MyAnswerListener(TRUE,quiz,getApplicationContext()));
        falseButton.setOnClickListener(new MyAnswerListener(FALSE,quiz,getApplicationContext()));

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
