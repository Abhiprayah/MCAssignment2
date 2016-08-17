package com.example.abhi.quizapp;

import java.io.Serializable;

/**
 * Created by Abhi on 14-08-2016.
 */
public class MathQuiz implements Quiz,Serializable {
    private String ans = "";

    public String generateQuestion() {
        int num = (int)(Math.random() * 1000) + 1;
        if(isPrime(num)) ans = "True";
        else ans = "False";
        return ("Is " + num + " a prime number?");
    }
    public boolean checkAnswer(String answer){
        return ans.equals(answer);
    }
    private boolean isPrime(int num)
    {
        if(num == 1) return false;
        if(num == 2) return true;
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
