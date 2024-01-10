/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.threads;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.constant.MyServerConstants;
import rs.ac.bg.fon.ps.domain.User;


/**
 *
 * @author Aleksandra
 */
public class ServerThread extends Thread{
    private ServerSocket serverSocket;
    private List<HandleClientThread> clients;
    Properties properties = new Properties();
    public ServerThread() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config/server.properties"));
                
        int port = Integer.parseInt(properties.getProperty(MyServerConstants.DB_CONFIG_PORT));
                
        serverSocket=new ServerSocket(port);
        clients=new ArrayList<>();
    }
    
                
 
          
    

    @Override
    public void run() {
        while(!serverSocket.isClosed()){
            try {
                System.out.println("Waiting for a client...");
                Socket socket=serverSocket.accept();
                HandleClientThread thread=new HandleClientThread(socket);
                thread.start();
                clients.add(thread);
                System.out.println("Client connected!");
            } catch (IOException ex) {
                 System.out.println("Server je zaustavljen!");
            }
        }
        stopAllThreads();
    }
    
    private void stopAllThreads(){
        for (HandleClientThread client : clients) {
            try {
                client.getSocket().close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
     public  void stopServerThread() throws IOException{
        serverSocket.close();
    }
   /* private List<User> getAllUsers(){
        List<User> users=new ArrayList<>();
        for (HandleClientThread client : clients) {
            users.add(client.getUser());
        }
        return users;
    }*/

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    
    
}
