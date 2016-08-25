package com.example.abhi.quizapp;

import java.io.Serializable;

/**
 * Created by Abhi on 14-08-2016.
 * Math's quiz class. Currently only has only one type of question.
 * Future support for various types of question easily possible although some may require GUI changes
 * Future changes - A new class of type question would allow this class to be more generic
 */
class MathQuiz implements Quiz,Serializable {
    private String ans;
    private String question;
    private int attempt; //Number of attempts at the current question
    private int num;

    /**
     * generateQuestion does most of the work for initialing a new question
     */
    public void generateQuestion() {
        attempt = 0;
        num = (int)(Math.random() * 1000) + 1;
        if(isPrime(num)) ans = "True";
        else ans = "False";
        question = "Is " + num + " a prime number?";
    }

    public int questionAsked(){
        return num;
    }

    /**
     * Returns the current question
     * @return - the current question
     */
    public String getQuestion(){
        return question;
    }

    /**
     * Returns the number of attempts for the current question
     * @return - number of attempts for the current question
     */
    public int getAttempts(){ return attempt;}

    /**
     * Check's whether the answer given by the user is correct or not
     * @param answer - The answer given by the user
     * @return - boolean variable indicating whether the answer is correct or not
     */
    public boolean checkAnswer(String answer){
        boolean result = ans.equals(answer);
        attempt++;
        if(attempt == 1 && result) MainActivity.updateScore();
        return result;
    }

    /**
     * utility function to check whether the number is prime or not
     * @param num - the number to check for primeness
     * @return - boolean variable indicating whether the number is prime or not
     */
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
