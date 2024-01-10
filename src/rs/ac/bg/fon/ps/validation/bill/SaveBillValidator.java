/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.validation.bill;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Bill;
import rs.ac.bg.fon.ps.domain.BillItem;
import rs.ac.bg.fon.ps.validation.Validation;

/**
 *
 * @author Aleksandra
 */
public class SaveBillValidator implements Validation{

    @Override
    public void validate(Object o) throws Exception {
        if (!(o instanceof Bill)) {
            throw new Exception("Greska, mora biti prosledjen racun.");
        }   
        Bill bill=(Bill)o;
        if (bill.getItems() instanceof List) {
            for(BillItem bi:bill.getItems()){
                if(bi.getQuantity()<0){
                    
                }
            }
            
            if (((List) o).size() > 0 && (((List) o).get(0) instanceof BillItem) ) {
            } else {
                throw new Exception("Greska. Mora biti prosledjena stavka racuna.");
            }
        }  
    }
    
}
