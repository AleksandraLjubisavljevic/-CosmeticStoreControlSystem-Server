/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.validation.bill;


import rs.ac.bg.fon.ps.domain.Bill;
import rs.ac.bg.fon.ps.validation.Validation;

/**
 *
 * @author Aleksandra
 */
public class DeleteBillValidator implements Validation{

    @Override
    public void validate(Object o) throws Exception {
        if (!(o instanceof Bill)) {
            throw new Exception("Greska, mora biti prosledjen racun.");
        }
        Bill b =  (Bill) o;
        if (b.getBillID() <= 0) {
            throw new Exception("Greska, id racuna mora biti veci od nule.");
        }    
    }
}
