/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework5server.startup;

import homework5server.controller.Controller;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Sarah
 */
public class TCPServer {
    
    private static final int port = 3333 ;
    private ServerSocket listeningSocket ;
    
    public void getConnections () {
        try {
            listeningSocket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = listeningSocket.accept();
                Thread newGame = new Thread(new Controller(clientSocket));
                newGame.setPriority(Thread.MAX_PRIORITY);
                newGame.start();
            } 
        } catch (IOException e) {
            System.err.println("Server failure.");
        }
            
    
    }
    
    
    
}
