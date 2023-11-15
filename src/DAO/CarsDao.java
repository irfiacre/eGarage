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
            String sql = "INSERT INTO cars(model,type,year,fault_description,owner_name,owner_email,status)"
                    + "VALUES (?,?,?,?,?,?,?)";                        
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, carObj.getModel());
            pst.setString(2, carObj.getType());
            pst.setString(3, carObj.getYear());
            pst.setString(4, carObj.getFault_description());
            pst.setString(5, carObj.getOwner_name());
            pst.setString(6, carObj.getOwner_email());
            pst.setString(7, carObj.getStatus());
            int rowAffected = pst.executeUpdate();
            con.close();
            if (rowAffected >= 1) {
                return "Car Added Successfuly!!";
            } else {
                return "Failed to add car!";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return "Server Error";
        }
    }
        
    public List<Cars> allCarRecords() {
        try {
            Connection con = DriverManager.getConnection(db_Url, username, passwd);
            String SQLquery = "select * from cars;";
            PreparedStatement pst = con.prepareStatement(SQLquery);
            ResultSet result = pst.executeQuery();
            List<Cars> carRecords = new ArrayList();
            while (result.next()) {
                Cars carObj = new Cars();
                carObj.setId(result.getInt("id"));
                carObj.setModel(result.getString("model"));
                carObj.setType(result.getString("type"));
                carObj.setYear(result.getString("year"));
                carObj.setFault_description(result.getString("fault_description"));
                carObj.setOwner_name(result.getString("owner_name"));
                carObj.setOwner_email(result.getString("owner_email"));
                carObj.setStatus(result.getString("status"));
                carRecords.add(carObj);
            }
            con.close();
            return carRecords;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public Cars findCarRecord(Integer carId) {
        try {
            Connection con = DriverManager.getConnection(db_Url, username, passwd);
            String SQLquery = "select * from cars where id=?;";
            PreparedStatement pst = con.prepareStatement(SQLquery);
            pst.setInt(1, carId);
            ResultSet result = pst.executeQuery();
            Cars carObj = new Cars();
            boolean flag=false;
            while (result.next()) {
                carObj.setId(result.getInt("id"));
                carObj.setModel(result.getString("model"));
                carObj.setType(result.getString("type"));
                carObj.setYear(result.getString("year"));
                carObj.setFault_description(result.getString("fault_description"));
                carObj.setOwner_name(result.getString("owner_name"));
                carObj.setOwner_email(result.getString("owner_email"));
                carObj.setStatus(result.getString("status"));
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
            Connection con = DriverManager.getConnection(db_Url, username, passwd);
            // Add Update query below
            String SQLquery = "update cars set model=?,type=?,year=?,fault_description=?,owner_email=?,status=? where owner_name=?;";
            PreparedStatement pst = con.prepareStatement(SQLquery);
            
            pst.setString(1, carObj.getModel());
            pst.setString(2, carObj.getType());
            pst.setString(3, carObj.getYear());
            pst.setString(4, carObj.getFault_description());
            pst.setString(5, carObj.getOwner_email());
            pst.setString(6, carObj.getStatus());
            pst.setString(7, carObj.getOwner_name());

            
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

    public String deleteCarPrepared(Cars carObj) {
        /* This method Deletes a Car's record from the Database records
        (Using Prepared Statement) */
        String returnMsg = "";
        try {
            Connection con = DriverManager.getConnection(db_Url, username, passwd);
            String SQLquery = "delete from cars where owner_name=?;";
            PreparedStatement pst = con.prepareStatement(SQLquery);
            pst.setString(1, carObj.getOwner_name());
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
    
    public Integer getCarsCount() {
        try {
            Connection con = DriverManager.getConnection(db_Url, username, passwd);
            String SQLquery = "select count(*) from cars;";
            PreparedStatement pst = con.prepareStatement(SQLquery);
            ResultSet result = pst.executeQuery();
            Cars carObj = new Cars();
            Integer carsCount =0;
            while (result.next()) {
                carsCount = result.getInt(1);
            }
            return carsCount;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
