/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.products;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.ResponseType;
import rs.ac.bg.fon.ps.db.DBBroker;
import rs.ac.bg.fon.ps.domain.DomainObject;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.so.AbstractSO;
import rs.ac.bg.fon.ps.validation.Validation;
import rs.ac.bg.fon.ps.validation.product.SaveProductValidator;


/**
 *
 * @author Aleksandra
 */

public class AddProductSO extends AbstractSO{
    

    @Override
    protected void precondition(DomainObject odo) throws Exception{
       Validation v = new SaveProductValidator();
        v.validate(odo);
        
    }

    @Override
    protected Response executeOperation(DomainObject odo) throws Exception{
        Response response=new Response();
        try{
        DBBroker.getInstance().insert(odo);
        response.setResult(null);
        response.setResponseType(ResponseType.SUCCESS);
        response.setMessage("Sistem je sacuvao novi kozmeticki proizvod");
        }catch (SQLException ex) {
            response.setResponseType(ResponseType.ERROR);
            throw new Exception("Sistem nije sacuvao novi kozmeticki proizvod.");
        }
        return response;
    }
       
}
    
    
    

