/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.users;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.ResponseType;
import rs.ac.bg.fon.ps.db.DBBroker;
import rs.ac.bg.fon.ps.domain.DomainObject;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 *
 * @author Aleksandra
 */
public class LogInUserSO extends AbstractSO{

    @Override
    protected Response executeOperation(DomainObject odo) throws Exception {
        Response response = new Response();
        try {
            List<DomainObject> list = odo.getList(DBBroker.getInstance().select(odo));
            if(list.isEmpty())
                throw new Exception("Neuspesno prijavljivanje na sistem.");
            response.setResult(list);
            response.setResponseType(ResponseType.SUCCESS);
            response.setMessage("Uspesno ste se prijavili na sistem.");
            
        } catch (Exception ex) {
            response.setResponseType(ResponseType.ERROR);
            throw new Exception("Neuspesno prijavljivanje na sistem.");
        }
        return response;
    }

    @Override
    protected void precondition(DomainObject odo) throws Exception {
        
    }
     
}
