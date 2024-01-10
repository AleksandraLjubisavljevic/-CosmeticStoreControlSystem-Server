/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.validation.user;


import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.validation.Validation;

/**
 *
 * @author Aleksandra
 */
public class SaveUserValidator implements Validation{

    @Override
    public void validate(Object o) throws Exception {
        if (!(o instanceof User)) {
            throw new Exception("Greska, mora biti prosledjen korisnik.");
        }   
    }
}
