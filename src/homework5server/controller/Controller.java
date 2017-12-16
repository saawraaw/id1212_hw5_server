/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework5server.controller;

import homework5server.model.Game;
import homework5server.net.ServerThread;
import java.net.Socket;

/**
 *
 * @author Sarah
 */
public class Controller implements Runnable { 
    private final Game game;
    private final ServerThread server ;
    private boolean stopPlaying = false ; 

    
    public Controller (Socket s) {
        game = new Game ();
        server = new ServerThread (s);
        
    }
    @Override
    public void run() {
        //server.sendDataToClient("Starting a New Game...\n");
        while (!stopPlaying) {
            if(!game.getWaitForNewGame())
                server.sendDataToClient(game.getState());
            String in = server.readClientData () ;
            in = in.toLowerCase() ;
            System.out.println("Client's data is received: " + in);
            interpreter (in);
        }
        server.disconnect();
    }
    
    private void interpreter (String in) {
        if (in.equals("quit game")) {
            //server.sendDataToClient("Quiting Game...\n");
                stopPlaying = true ;
        } else if (game.getGameFinished()) {
            if (in.equals("play")) {
                //server.sendDataToClient("Starting a New Game...\n");
                game.newGame();
            } 
        } else {
            System.out.println("Will play the input");
            game.play(in);
        }
            
        
    }
    

}
