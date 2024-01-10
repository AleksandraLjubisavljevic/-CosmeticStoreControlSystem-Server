/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.ResponseType;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.db.DBBroker;
import rs.ac.bg.fon.ps.domain.DomainObject;
import rs.ac.bg.fon.ps.domain.Product;

/**
 *
 * @author Aleksandra
 */
public abstract class AbstractSO {
      public Response executeTransaction(DomainObject odo) throws Exception {
        Response response = new Response();
        try {
            DBBroker.getInstance().openConnection();
            precondition(odo);
            response = executeOperation(odo);
            DBBroker.getInstance().commit();
        } catch (Exception ex) {
            Logger.getLogger(DomainObject.class.getName()).log(Level.SEVERE, null, ex);
            DBBroker.getInstance().rollback();
            response.setResponseType(ResponseType.ERROR);
            response.setMessage(ex.getMessage());
          
            
        }finally {
            try {
                DBBroker.getInstance().closeConnection();
            } catch (SQLException ex) {
                response.setResponseType(ResponseType.ERROR);
                response.setMessage(ex.getMessage());
            }
        }
        
        return response;
    }

 
    protected abstract Response executeOperation(DomainObject odo) throws Exception;
    protected abstract void precondition(DomainObject odo) throws Exception;

   
}
