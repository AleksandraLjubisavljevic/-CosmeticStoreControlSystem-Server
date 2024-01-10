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
public class DeleteProductValidator implements Validation{

    @Override
    public void validate(Object o) throws Exception {
        if (!(o instanceof Product)) {
            throw new Exception("Greska, mora biti prosledjen proizvod.");
        }
        Product p =  (Product) o;
        if (p.getId() <= 0) {
            throw new Exception("Greska, id proizvoda mora biti veci od nule.");
        }    
    }
    
}
