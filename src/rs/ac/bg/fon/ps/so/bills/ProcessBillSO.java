/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.bills;

import java.sql.SQLException;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.ResponseType;
import rs.ac.bg.fon.ps.db.DBBroker;
import rs.ac.bg.fon.ps.domain.Bill;
import rs.ac.bg.fon.ps.domain.BillItem;
import rs.ac.bg.fon.ps.domain.DomainObject;
import rs.ac.bg.fon.ps.domain.Status;
import rs.ac.bg.fon.ps.so.AbstractSO;
import rs.ac.bg.fon.ps.validation.Validation;
import rs.ac.bg.fon.ps.validation.bill.SaveBillValidator;

/**
 *
 * @author Aleksandra
 */
public class ProcessBillSO extends AbstractSO{

    @Override
    protected Response executeOperation(DomainObject odo) throws Exception {
        Response response=new Response();
        try{
        DBBroker.getInstance().update(odo);
        Bill b = (Bill) odo;
        response.setResult(null);
        response.setResponseType(ResponseType.SUCCESS);
        response.setMessage("Sistem je obradio racun");
        }catch (SQLException ex) {
            response.setResponseType(ResponseType.ERROR);
            throw new Exception("Sistem ne moze da obradi racun");
            
        }
        return response;
    }

    @Override
    protected void precondition(DomainObject odo) throws Exception {
        Validation v = new SaveBillValidator();
        v.validate(odo);
    }
    
}
