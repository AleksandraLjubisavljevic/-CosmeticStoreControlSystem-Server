/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.threads;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.communication.Operations;
import rs.ac.bg.fon.ps.communication.Receiver;
import rs.ac.bg.fon.ps.communication.Request;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.Sender;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Distributor;
import rs.ac.bg.fon.ps.domain.DomainObject;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Aleksandra
 */
public class HandleClientThread extends Thread{
    
    private Socket socket;
    private User user;
    private DomainObject odo;
    private Distributor distributors;
    
    public HandleClientThread(Socket socket) throws IOException {
        this.socket = socket;
    }

    @Override
    public void run() {
        while(!socket.isClosed()){
            try {
                Request request = (Request) new Receiver(socket).receive();
                Response response=handleRequest(request);
                new Sender(socket).send(response);
            } catch (Exception ex) {
                Logger.getLogger(HandleClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
    
    private Response handleRequest(Request request) throws Exception{
       
        int operation=request.getOperation();
        switch(operation){
            case Operations.LOGIN:
                return Controller.getInstance().logInUser((DomainObject) request.getArgument());
            case Operations.GET_ALL_DISTRIBUTORS:
                return Controller.getInstance().getAllDistributors((DomainObject) request.getArgument());
            case Operations.ADD_PRODUCT:
                return Controller.getInstance().saveProduct((DomainObject) request.getArgument());
            case Operations.GET_ALL_PRODUCTS:
                return Controller.getInstance().getAllProducts((DomainObject) request.getArgument());
            case Operations.UPDATE_PRODUCT:
                return Controller.getInstance().updateProduct((DomainObject) request.getArgument());
            case Operations.DELETE_PRODUCT:
                return Controller.getInstance().deleteProduct((DomainObject) request.getArgument());
            case Operations.ADD_BILL:
                return Controller.getInstance().saveBill((DomainObject) request.getArgument());
            case Operations.GET_ALL_BILLS:
                return Controller.getInstance().getAllBills((DomainObject) request.getArgument());
            case Operations.CANCEL_BILL:
                return Controller.getInstance().deleteBill((DomainObject) request.getArgument());
            case Operations.UPDATE_BILL:
                return Controller.getInstance().updateBill((DomainObject) request.getArgument());
            case Operations.UCITAJ_PROIZVOD:
                return Controller.getInstance().ucitajProizvod((DomainObject) request.getArgument());
            case Operations.UCITAJ_RACUN:
                return Controller.getInstance().ucitajRacun((DomainObject) request.getArgument());
            case Operations.PROCESS_BILL:
                return Controller.getInstance().processBill((DomainObject) request.getArgument());
           
                    
        }
        return null;
    }

    public User getUser() {
        return user;
    }


}
