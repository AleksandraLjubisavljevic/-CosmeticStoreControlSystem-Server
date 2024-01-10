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
public class DeleteUserValidator implements Validation{

    @Override
    public void validate(Object o) throws Exception {
        if (!(o instanceof User)) {
            throw new Exception("Greska, mora biti prosledjen korisnik.");
        }
        User u =  (User) o;
        if (u.getId() <= 0) {
            throw new Exception("Greska, id korisnika mora biti veci od nule.");
        }        }
    
}
