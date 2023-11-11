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
import model.Users;

/**
 *
 * @author busyDev
 */
public class UsersDao {
    private String db_Url = "jdbc:mysql://localhost:3306/egarage_managment_system_db";
    private String username = "root";
    private String passwd = "12345";
    
    public String createUserPrepared(Users userObj) {
        try {
            // Create Connection
            Connection con = DriverManager.getConnection(db_Url, username, passwd);
            String sql = "INSERT INTO users(firstName,lastName,username,email,role,password)"
                    + "VALUES (?,?,?,?,?,?)";                        
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userObj.getFirstName());
            pst.setString(2, userObj.getLastName());
            pst.setString(3, userObj.getUsername());
            pst.setString(4, userObj.getEmail());
            pst.setString(5, userObj.getRole());
            pst.setString(6, userObj.getPassword());
            int rowAffected = pst.executeUpdate();
            con.close();
            if (rowAffected >= 1) {
                return "User Created Successfuly!!";
            } else {
                return "Failed to create user!";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return "Server Error";
        }
    }
        
    public List<Users> allUserRecords() {
        try {
            Connection con = DriverManager.getConnection(db_Url, username, passwd);
            String SQLquery = "select * from users;";
            PreparedStatement pst = con.prepareStatement(SQLquery);
            ResultSet result = pst.executeQuery();
            List<Users> userRecords = new ArrayList();
            while (result.next()) {
                Users userObj = new Users();
                userObj.setId(result.getInt("id"));
                userObj.setFirstName(result.getString("firstName"));
                userObj.setLastName(result.getString("lastName"));
                userObj.setUsername(result.getString("username"));
                userObj.setEmail(result.getString("email"));
                userObj.setRole(result.getString("role"));
                userRecords.add(userObj);
            }
            con.close();
            return userRecords;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public Users findUserRecord(String userUsername) {
        try {
            Connection con = DriverManager.getConnection(db_Url, username, passwd);
            String SQLquery = "select * from users where username=?;";
            PreparedStatement pst = con.prepareStatement(SQLquery);
            pst.setString(1, userUsername);
            ResultSet result = pst.executeQuery();
            Users userObj = new Users();
            boolean flag=false;
            while (result.next()) {
                userObj.setId(result.getInt("id"));
                userObj.setFirstName(result.getString("firstName"));
                userObj.setLastName(result.getString("lastName"));
                userObj.setUsername(result.getString("username"));
                userObj.setEmail(result.getString("email"));
                userObj.setRole(result.getString("role"));
                userObj.setPassword(result.getString("password"));
                flag=true;
            }
            con.close();
            if(flag){
                return userObj;
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public String updateUserPrepared(Users userObj) {
        String returnMsg = "";
        try {
            Connection con = DriverManager.getConnection(db_Url, username, passwd);
            // Add Update query below
            String SQLquery = "update users set firstName=?,lastName=?,email=?,role=? where username=?;";
            PreparedStatement pst = con.prepareStatement(SQLquery);
            pst.setString(1, userObj.getFirstName());
            pst.setString(2, userObj.getLastName());
            pst.setString(3, userObj.getEmail());
            pst.setString(4, userObj.getRole());
            pst.setString(5, userObj.getUsername());
            int rowAffected = pst.executeUpdate();
            con.close();
            if (rowAffected >= 1) {
                returnMsg = "User Record Updated";
            } else {
                returnMsg = "User Record not Updated";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            returnMsg = "Server Error";
        }

        return returnMsg;
    }

    public String deletePatientPrepared(Users userObj) {
        /* This method Deletes a Patient's record from the Database records
        (Using Prepared Statement) */
        String returnMsg = "";
        try {
            Connection con = DriverManager.getConnection(db_Url, username, passwd);
            String SQLquery = "delete from users where username=?;";
            PreparedStatement pst = con.prepareStatement(SQLquery);
            pst.setString(1, userObj.getUsername());
            int rowAffected = pst.executeUpdate();
            con.close();
            if (rowAffected >= 1) {
                returnMsg = "User Record Deleted Successfully!!";
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
