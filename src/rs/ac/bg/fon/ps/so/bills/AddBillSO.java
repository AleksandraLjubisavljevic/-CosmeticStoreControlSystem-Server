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
import rs.ac.bg.fon.ps.so.AbstractSO;
import rs.ac.bg.fon.ps.validation.Validation;
import rs.ac.bg.fon.ps.validation.bill.SaveBillItemsValidator;
import rs.ac.bg.fon.ps.validation.bill.SaveBillValidator;

/**
 *
 * @author Aleksandra
 */
public class AddBillSO extends AbstractSO{

    @Override
    protected void precondition(DomainObject odo) throws Exception {
        Validation v = new SaveBillValidator();
        
        v.validate(odo);
        
    }

    @Override
    protected Response executeOperation(DomainObject odo) throws Exception {
        Response response = new Response();
        try {
            DBBroker.getInstance().insert(odo);
            Bill b = (Bill) odo;
            b.setBillID(DBBroker.getInstance().getID(odo));
            for (BillItem bi : b.getItems()) {
                Validation v1=new SaveBillItemsValidator();
                v1.validate(bi);
                bi.setBill(b);
                DBBroker.getInstance().insert(bi);
            }
            response.setResult(null);
            response.setResponseType(ResponseType.SUCCESS);
            response.setMessage("Sistem je sacuvao novi racun (ID="+ b.getBillID()+" )");
           
        } catch (SQLException ex) {
            response.setResult(ResponseType.ERROR);
            throw new Exception("Sistem ne moze da sacuva racun");
            
        }
        return response;
        
    }
  
    
}
