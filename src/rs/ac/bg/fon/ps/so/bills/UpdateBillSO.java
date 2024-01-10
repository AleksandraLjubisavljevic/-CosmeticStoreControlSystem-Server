/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.bills;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.ResponseType;
import rs.ac.bg.fon.ps.db.DBBroker;
import rs.ac.bg.fon.ps.domain.Bill;
import rs.ac.bg.fon.ps.domain.BillItem;
import rs.ac.bg.fon.ps.domain.DomainObject;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Status;
import rs.ac.bg.fon.ps.so.AbstractSO;
import rs.ac.bg.fon.ps.validation.Validation;
import rs.ac.bg.fon.ps.validation.bill.SaveBillValidator;

/**
 *
 * @author Aleksandra
 */
public class UpdateBillSO extends AbstractSO{

    @Override
    protected Response executeOperation(DomainObject odo) throws Exception {
          
        Response response=new Response();
        try{
        DBBroker.getInstance().update(odo);
        Bill b = (Bill) odo;
           
        for (BillItem bi : b.getItems()) {
            if(bi.getStatus().equals(Status.NEW))
                DBBroker.getInstance().insert(bi);
                   
            if(bi.getStatus().equals(Status.UPDATE))
                DBBroker.getInstance().update(bi);
                       
            if(bi.getStatus().equals(Status.DELETE))
                DBBroker.getInstance().delete(bi);
                
                    
            }
       
        response.setResult(null);
        response.setResponseType(ResponseType.SUCCESS);
        response.setMessage("Sistem je izmenio racun (ID= "+b.getBillID()+" )");
        }catch (SQLException ex) {
            response.setResponseType(ResponseType.ERROR);
            throw new Exception("Sistem ne moze da izmeni racun");
            
        }
        return response;
    }

    @Override
    protected void precondition(DomainObject odo) throws Exception {
       Validation v = new SaveBillValidator();
        v.validate(odo);
    }
   
}
