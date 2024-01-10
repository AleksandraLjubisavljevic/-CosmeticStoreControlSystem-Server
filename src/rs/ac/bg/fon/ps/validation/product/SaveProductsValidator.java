/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.validation.product;


import java.util.List;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.validation.Validation;

/**
 *
 * @author Aleksandra
 */
public class SaveProductsValidator implements Validation{

    @Override
    public void validate(Object o) throws Exception {
        if (o instanceof List) {
            if (((List) o).size() > 0 && (((List) o).get(0) instanceof Product)) {
            } else {
                throw new Exception("Greska. Mora biti prosledjena lista proizvoda.");
            }
        }      
    }
    
}
