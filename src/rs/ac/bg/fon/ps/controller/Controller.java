/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.domain.Bill;
import rs.ac.bg.fon.ps.domain.Distributor;
import rs.ac.bg.fon.ps.domain.DomainObject;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.so.AbstractSO;
import rs.ac.bg.fon.ps.so.bills.AddBillSO;
import rs.ac.bg.fon.ps.so.bills.DeleteBillSO;
import rs.ac.bg.fon.ps.so.bills.GetAllBillsSO;
import rs.ac.bg.fon.ps.so.bills.GetBillSO;
import rs.ac.bg.fon.ps.so.bills.ProcessBillSO;
import rs.ac.bg.fon.ps.so.bills.UpdateBillSO;
import rs.ac.bg.fon.ps.so.distributors.GetAllDistributorsSO;
import rs.ac.bg.fon.ps.so.products.AddProductSO;
import rs.ac.bg.fon.ps.so.products.DeleteProductSO;
import rs.ac.bg.fon.ps.so.products.GetAllProductsSO;
import rs.ac.bg.fon.ps.so.products.GetProductSO;
import rs.ac.bg.fon.ps.so.products.UpdateProductSO;
import rs.ac.bg.fon.ps.so.users.LogInUserSO;

/**
 *
 * @author Aleksandra
 */

public class Controller {

    private static Controller instance;
  

    private Controller() {
        
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Response logInUser(DomainObject odo) throws Exception {
        return new LogInUserSO().executeTransaction(odo);
    } 

    public Response getAllProducts(DomainObject odo) throws Exception {
        return new GetAllProductsSO().executeTransaction(odo);
    }
    public Response ucitajProizvod(DomainObject odo) throws Exception {
        return new GetProductSO().executeTransaction(odo);
    }
    public Response ucitajRacun(DomainObject odo) throws Exception {
        return new GetBillSO().executeTransaction(odo);
    }
    public Response getAllDistributors(DomainObject odo) throws Exception {
        return new GetAllDistributorsSO().executeTransaction(odo);
    }
 
    public Response saveProduct(DomainObject odo) throws Exception {
        return new AddProductSO().executeTransaction(odo);
    } 

    public Response updateProduct(DomainObject odo) throws Exception {
        return new UpdateProductSO().executeTransaction(odo);
    }
  
    public Response deleteProduct(DomainObject odo) throws Exception {
        return new DeleteProductSO().executeTransaction(odo);
    }

    public Response saveBill(DomainObject odo) throws Exception {
        return new AddBillSO().executeTransaction(odo);
    } 
    
    public Response getAllBills(DomainObject odo) throws Exception {
        return new GetAllBillsSO().executeTransaction(odo);
    }
    
    public Response updateBill(DomainObject odo) throws Exception {
        return new UpdateBillSO().executeTransaction(odo);
    }
    
    public Response deleteBill(DomainObject odo) throws Exception {
        return new DeleteBillSO().executeTransaction(odo);
     
    }
    public Response processBill(DomainObject odo) throws Exception {
        return new ProcessBillSO().executeTransaction(odo);
    }
}

