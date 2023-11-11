/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cars;

/**
 *
 * @author busyDev
 */
public class CarsDao {
    private String db_Url = "jdbc:mysql://localhost:3306/egarage_managment_system_db";
    private String username = "root";
    private String passwd = "12345";
    
    public String createCarPrepared(Cars carObj) {
        try {
            // Create Connection
            Connection con = DriverManager.getConnection(db_Url, username, passwd);
            String sql = "INSERT INTO cars(firstName,lastName,carname,email,role,password)"
                    + "VALUES (?,?,?,?,?,?)";                        
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, carObj.getFirstName());
            pst.setString(2, carObj.getLastName());
            pst.setString(3, carObj.getCarname());
            pst.setString(4, carObj.getEmail());
            pst.setString(5, carObj.getRole());
            pst.setString(6, carObj.getPassword());
            int rowAffected = pst.executeUpdate();
            con.close();
            if (rowAffected >= 1) {
                return "Car Created Successfuly!!";
            } else {
                return "Failed to create car!";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return "Server Error";
        }
    }
        
    public List<Cars> allCarRecords() {
        try {
            Connection con = DriverManager.getConnection(db_Url, carname, passwd);
            String SQLquery = "select * from cars;";
            PreparedStatement pst = con.prepareStatement(SQLquery);
            ResultSet result = pst.executeQuery();
            List<Cars> carRecords = new ArrayList();
            while (result.next()) {
                Cars carObj = new Cars();
                carObj.setId(result.getInt("id"));
                carObj.setFirstName(result.getString("firstName"));
                carObj.setLastName(result.getString("lastName"));
                carObj.setCarname(result.getString("carname"));
                carObj.setEmail(result.getString("email"));
                carObj.setRole(result.getString("role"));
                carRecords.add(carObj);
            }
            con.close();
            return carRecords;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public Cars findCarRecord(String carCarname) {
        try {
            Connection con = DriverManager.getConnection(db_Url, carname, passwd);
            String SQLquery = "select * from cars where carname=?;";
            PreparedStatement pst = con.prepareStatement(SQLquery);
            pst.setString(1, carCarname);
            ResultSet result = pst.executeQuery();
            Cars carObj = new Cars();
            boolean flag=false;
            while (result.next()) {
                carObj.setId(result.getInt("id"));
                carObj.setFirstName(result.getString("firstName"));
                carObj.setLastName(result.getString("lastName"));
                carObj.setCarname(result.getString("carname"));
                carObj.setEmail(result.getString("email"));
                carObj.setRole(result.getString("role"));
                carObj.setPassword(result.getString("password"));
                flag=true;
            }
            con.close();
            if(flag){
                return carObj;
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public String updateCarPrepared(Cars carObj) {
        String returnMsg = "";
        try {
            Connection con = DriverManager.getConnection(db_Url, carname, passwd);
            // Add Update query below
            String SQLquery = "update cars set firstName=?,lastName=?,email=?,role=? where carname=?;";
            PreparedStatement pst = con.prepareStatement(SQLquery);
            pst.setString(1, carObj.getFirstName());
            pst.setString(2, carObj.getLastName());
            pst.setString(3, carObj.getEmail());
            pst.setString(4, carObj.getRole());
            pst.setString(5, carObj.getCarname());
            int rowAffected = pst.executeUpdate();
            con.close();
            if (rowAffected >= 1) {
                returnMsg = "Car Record Updated";
            } else {
                returnMsg = "Car Record not Updated";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            returnMsg = "Server Error";
        }

        return returnMsg;
    }

    public String deletePatientPrepared(Cars carObj) {
        /* This method Deletes a Patient's record from the Database records
        (Using Prepared Statement) */
        String returnMsg = "";
        try {
            Connection con = DriverManager.getConnection(db_Url, carname, passwd);
            String SQLquery = "delete from cars where carname=?;";
            PreparedStatement pst = con.prepareStatement(SQLquery);
            pst.setString(1, carObj.getCarname());
            int rowAffected = pst.executeUpdate();
            con.close();
            if (rowAffected >= 1) {
                returnMsg = "Car Record Deleted Successfully!!";
            } else {
                returnMsg = "Unable to delete record";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            returnMsg = "Server Error";
        }

        return returnMsg;
    }
        
    
}
