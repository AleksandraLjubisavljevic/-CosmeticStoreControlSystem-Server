/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.bills;

import java.util.List;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.ResponseType;
import rs.ac.bg.fon.ps.db.DBBroker;
import rs.ac.bg.fon.ps.domain.DomainObject;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 *
 * @author Aleksandra
 */
public class GetBillSO extends AbstractSO{

    @Override
    protected Response executeOperation(DomainObject odo) throws Exception {
        Response response = new Response();
        try {
            List<DomainObject> list = odo.getList(DBBroker.getInstance().select(odo));
            if(list.isEmpty())
                throw new Exception("Neuspesno ucitavanje racuna.");
            response.setResult(list);
            response.setResponseType(ResponseType.SUCCESS);
            response.setMessage("Uspesno ucitavanje racuna.");
            
        } catch (Exception ex) {
            response.setResponseType(ResponseType.ERROR);
            throw new Exception("Neuspesno ucitavanje.");
        }
        return response;
    }

    @Override
    protected void precondition(DomainObject odo) throws Exception {
        
    }
    
}
