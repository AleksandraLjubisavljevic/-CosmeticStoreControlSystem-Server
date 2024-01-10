/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.validation.bill;


import java.util.List;
import rs.ac.bg.fon.ps.domain.Bill;
import rs.ac.bg.fon.ps.validation.Validation;

/**
 *
 * @author Aleksandra
 */
public class SaveBillsValidator implements Validation{

    @Override
    public void validate(Object o) throws Exception {
        if (o instanceof List) {
            if (((List) o).size() > 0 && (((List) o).get(0) instanceof Bill)) {
            } else {
                throw new Exception("Greska. Mora biti prosledjena lista racuna.");
            }
        }    
    }
    
}
