/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.distributors;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.ResponseType;
import rs.ac.bg.fon.ps.db.DBBroker;
import rs.ac.bg.fon.ps.domain.Distributor;
import rs.ac.bg.fon.ps.domain.DomainObject;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 *
 * @author Aleksandra
 */
public class GetAllDistributorsSO extends AbstractSO{

    @Override
    protected void precondition(DomainObject odo) throws Exception {
       
    }

    @Override
    protected Response executeOperation(DomainObject odo) throws Exception {
       
       Response response=new Response();
       
        try{
        List<DomainObject> list = odo.getList(DBBroker.getInstance().select(odo));

        response.setResult(list);
        response.setResponseType(ResponseType.SUCCESS);
        response.setMessage("Sistem je pronasao distributere po zadatim kriterijumima pretrage.");
        }catch (SQLException ex) {
            response.setResponseType(ResponseType.ERROR);
            throw new Exception("Sistem nije pronasao distributere");
        }
        return response;
    }
}


