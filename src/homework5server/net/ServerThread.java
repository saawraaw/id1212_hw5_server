/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework5server.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Sarah
 */
public class ServerThread  {
    
    private Socket clientSocket ; 
    private BufferedReader fromClient ; 
    PrintWriter toClient ;
    private final int LINGER_TIME = 5000 ;
 
    public ServerThread(Socket s) {
        try {
            clientSocket = s ;
            clientSocket.setSoLinger(true, LINGER_TIME);
            fromClient = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
            toClient = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
            
     
    }
    
    
    public String readClientData() {
        String input = new String () ;
        try {
            input = fromClient.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input ;
             
    }
    
    public void sendDataToClient (String s) {
        toClient.print(s);
        toClient.flush () ;
    }
    
    public void disconnect () {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
  
    
}
