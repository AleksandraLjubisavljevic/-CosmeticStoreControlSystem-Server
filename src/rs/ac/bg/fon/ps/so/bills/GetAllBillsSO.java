/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.bills;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.ResponseType;
import rs.ac.bg.fon.ps.db.DBBroker;
import rs.ac.bg.fon.ps.domain.Bill;
import rs.ac.bg.fon.ps.domain.BillItem;
import rs.ac.bg.fon.ps.domain.DomainObject;
import rs.ac.bg.fon.ps.domain.Status;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 *
 * @author Aleksandra
 */
public class GetAllBillsSO extends AbstractSO{

    @Override
    protected Response executeOperation(DomainObject odo) throws Exception {
             Response response1 = new Response();
        try {
            List<DomainObject> list = odo.getList(DBBroker.getInstance().select(odo));
            ArrayList<Bill> listBills = new ArrayList<>();
            
            for (DomainObject od : list) {
                ArrayList<BillItem> listBillItems = new ArrayList<>();
                Bill b = (Bill) od;
                BillItem bi = new BillItem(b, 0l, new BigDecimal(0), 0, null, "");
                List<DomainObject> listBI = bi.getList(DBBroker.getInstance().select(bi));
                for (DomainObject bItem : listBI) {
                    BillItem bit = (BillItem) bItem;
                    bit.setBill(b);
                    listBillItems.add(bit);
                }
                b.setItems(listBillItems);
                listBills.add(b);
           
            }
            response1.setResult(listBills);
            response1.setResponseType(ResponseType.SUCCESS);
            response1.setMessage("Sistem je pronasao racune po zadatom kriterijumu pretrage.");

        } catch (SQLException ex) {
            Logger.getLogger(GetAllBillsSO.class.getName()).log(Level.SEVERE, null, ex);
            response1.setResponseType(ResponseType.ERROR);
            throw new Exception("Sistem nije pronasao racune po zadatom kriterijumu pretrage");
        }
        return response1;
             
        
    }
    
    @Override
    protected void precondition(DomainObject odo) throws Exception {
        
    }
    
}
   

    
    
