/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.db;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.constant.MyServerConstants;
import rs.ac.bg.fon.ps.domain.DomainObject;




/**
 *
 * @author Aleksandra
 */
public class DBBroker {

    private static DBBroker instance;
    private Connection connection;
    
    private DBBroker() {
    }

    public static DBBroker getInstance() {
        if(instance == null)
            instance = new DBBroker();
        return instance;
    }
    
    public void openConnection() throws SQLException, FileNotFoundException, IOException{
             if(connection==null || connection.isClosed()){
            try {
  
                Properties properties = new Properties();
                properties.load(new FileInputStream("config/dbconfig.properties"));
                
                String url = properties.getProperty(MyServerConstants.DB_CONFIG_URL);
                String user = properties.getProperty(MyServerConstants.DB_CONFIG_USERNAME);
                String password = properties.getProperty("password");
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            } catch (SQLException ex) {
                System.out.println("Neuspesno uspostavljanje konekcije!\n" + ex.getMessage());
                throw ex;
            }
        }
    }
     public void closeConnection() throws SQLException, Exception{
       if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Greska prilikom raskidanja konekcije sa bazom!\n" + ex.getMessage());
            }
        }
    }
    
    public void commit() throws Exception{
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Greska prilikom potvrdjivanja transakcije!\n" + ex.getMessage());
            }
        }
    }
    
    public void rollback() throws Exception{
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Greska prilikom ponistavanja transakcije!\n" + ex.getMessage());
            }
        }
    }
    
    public void insert(DomainObject odo) throws SQLException{
        String sql = "INSERT INTO "+odo.getTableName()+"("+odo.getColumnsNames()+")"+" VALUES ("+odo.getAttributeValuesForInsert()+")";
        System.out.println(sql);
        Statement s = connection.createStatement();
        s.executeUpdate(sql);
        System.out.println("Uspesno kreiran " + odo.getTableName() + " u bazi!");
        s.close();

    }
    
    
    public void update(DomainObject odo) throws SQLException{
        String sql = "UPDATE "+odo.getTableName()+" SET "+odo.getAttributeValuesForUpdate()+" WHERE "+odo.getWhereCondition();
        System.out.println(sql);
        Statement s = connection.createStatement();
        s.executeUpdate(sql);
        s.close();
    }
    
    public void delete(DomainObject odo) throws SQLException{
       
        String sql = "DELETE FROM "+odo.getTableName()+" WHERE "+odo.getWhereCondition();
        System.out.println(sql);
        Statement s = connection.createStatement();
        s.executeUpdate(sql);
        s.close();
        System.out.println("Uspesno obrisan " + odo.getTableName() + " iz baze!");
       
    }   
    
    public ResultSet select(DomainObject odo) throws SQLException{
        String sql = "SELECT "+odo.getColumnsForSelect()+" FROM "+odo.getTableName() +" as "+odo.getAlias()
                +odo.getJoinCondition()+odo.getWhereConditionSelect()+odo.getGroup();
        System.out.println(sql);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(sql);
        return rs;
    }

    public Long getID(DomainObject odo) throws SQLException{
        Long max = 0l;
        String sql = "SELECT max("+odo.getMaxID()+") as max FROM "+odo.getTableName();
        System.out.println(sql);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while(rs.next())
            max = rs.getLong("max");
        
        return max;
    }
   
}
