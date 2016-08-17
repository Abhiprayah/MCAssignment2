package com.example.abhi.quizapp;

import java.io.Serializable;

/**
 * Created by Abhi on 14-08-2016.
 */
public class MathQuiz implements Quiz,Serializable {
    private String ans;
    private String question;
    private int attempt;

    public void generateQuestion() {
        attempt = 0;
        int num = (int)(Math.random() * 1000) + 1;
        if(isPrime(num)) ans = "True";
        else ans = "False";
        question = "Is " + num + " a prime number?";
    }
    public String getQuestion(){
        return question;
    }
    public boolean checkAnswer(String answer){
        boolean result = ans.equals(answer);
        attempt++;
        if(attempt == 1 && result) MainActivity.updateScore();
        return result;
    }
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
}
