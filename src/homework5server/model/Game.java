/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework5server.model;


/**
 *
 * @author Sarah
 */
public class Game {
    
    private int attemptsLeft ; 
    private String currentWord ; 
    private boolean gameFinished ;
    private String chosenWord; 
    public boolean gameWon ; 
    private boolean waitForNewGame = false ; 
    private int totalScore = 0 ;
    private WordChoser wordChoser = new WordChoser ();
    
    public Game (){
        newGame () ;
    }
    
    
    private void changeState (String input){
        System.out.println("the input and the length of the input: " + input + "   " + input.length());
        if (input.length()==1){
            System.out.println("length is one");
            if (!chosenWord.contains(input)){
                System.out.println("chosen word did not contain this letter");
                wrongGuess ();
            } else {
                System.out.println("chosen word contained this letter");
                charGuessCorrect (input.charAt(0));
            }
        } else {
            System.out.println("chosen word is equal to this word");
            if (chosenWord.equals(input)){
                wordGuessCorrect ();
            } else {
                wrongGuess () ;
            }
        }
            
    }
    
    public void wrongGuess (){
        attemptsLeft --;
        if (attemptsLeft ==0){
            gameFinished = true ;
            totalScore -- ;
        }
    }
    

    private void charGuessCorrect (char c){
        int i = chosenWord.indexOf(c) ;
        String temp = currentWord ;
        if (i==0){
            temp = c + temp.substring(1);
        } else {
            temp = temp.substring(0,i) + c + temp.substring(i+1);
        }
        while (true){
            i = chosenWord.indexOf(c , i+1) ;
            if (i==-1)
                break ; 
            else {
                temp = temp.substring(0,i) + c + temp.substring(i+1);
            }
        }
        currentWord = temp; 
        if (chosenWord.equals(currentWord)){
            wordGuessCorrect ();
        }
    }
    
    private void wordGuessCorrect (){
        currentWord = chosenWord;
        gameFinished  = true ;
        gameWon = true ; 
        totalScore ++ ;
    }
    
    public void play (String input) {
        String inputWord = input.toLowerCase();
        changeState (inputWord);
        
               
    }
                
    public String getChosenWord () {
        return chosenWord ;
    }
    
    public String getState () {
        String s ;
        if (gameFinished) {
            waitForNewGame = true ; 
            s = "1#" ;
        } else {
            s = "0#";
        }
        if (gameWon) {
                s +=  "1#" ;     
        } else {
            s += "0#" ;
        }
        s += totalScore + "#" ;
        s += attemptsLeft + "#";
        s += insertSpaces (currentWord) + "#" ;
        s += insertSpaces (chosenWord) + "\n" ;
        System.out.print(s);
        return s ; 
    }
    
    public void newGame () {
        
        chosenWord = wordChoser.getChosenWord() ; 
        System.out.println(chosenWord);
        gameFinished = false ;
        attemptsLeft = chosenWord.length();
        currentWord = new String ();
        for(int i=0 ;i<attemptsLeft ; i++){
            currentWord += "_" ; 
        }
        gameWon = false ;
        waitForNewGame = false ; 
        //System.out.println(attemptsLeft);
        //System.out.println(currentWord);
    }
    
    public boolean getGameFinished () {
        return gameFinished ;
    }
    
    public boolean getGameWon () {
        return gameWon ;
    }
    
    public boolean getWaitForNewGame () {
        return waitForNewGame ;
    }
    
    private String insertSpaces (String in) {
        String s = new String () ;
        for (int i=0 ; i<in.length();i++){
                s += in.charAt(i) + " " ;
        }
        return s ;
    }
   
    
    
    
    
}

