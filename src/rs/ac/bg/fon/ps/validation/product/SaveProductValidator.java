/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.validation.product;


import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.validation.Validation;

/**
 *
 * @author Aleksandra
 */
public class SaveProductValidator implements Validation{

    @Override
    public void validate(Object o) throws Exception {
        if (!(o instanceof Product)) {
            throw new Exception("Greska, mora biti prosledjen proizvod.");
        }   
    }
    
}
