/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import DAO.CarsDao;
import DAO.UsersDao;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import view.ModernAdminView;
import view.ModernCashierView;
import view.ModernMechanicView;
import view.ModernLoginView;
import java.util.regex.*;
import model.Cars;
import model.Users;

/**
 *
 * @author busyDev
 */
public class Helpers {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =  Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public void navigateToModernAdminView(String username) {
        ModernAdminView adminFrame = new ModernAdminView(username);
        ModernLoginView loginFrame = new ModernLoginView();
        ModernMechanicView mechFrame = new ModernMechanicView();
        ModernCashierView cashFrame = new ModernCashierView();

        loginFrame.setVisible(false);
        mechFrame.setVisible(false);
        cashFrame.setVisible(false);
        adminFrame.setVisible(true);
    }

    public void navigateToModernCashierView(String username) {
       ModernAdminView adminFrame = new ModernAdminView();
        ModernLoginView loginFrame = new ModernLoginView();
        ModernMechanicView mechFrame = new ModernMechanicView();
        ModernCashierView cashFrame = new ModernCashierView(username);

        loginFrame.setVisible(false);
        mechFrame.setVisible(false);
        cashFrame.setVisible(true);
        adminFrame.setVisible(false);
    }

    public void navigateToModernMechanicView(String username) {
        ModernAdminView adminFrame = new ModernAdminView();
        ModernLoginView loginFrame = new ModernLoginView();
        ModernMechanicView mechFrame = new ModernMechanicView(username);
        ModernCashierView cashFrame = new ModernCashierView();

        loginFrame.setVisible(false);
        mechFrame.setVisible(true);
        cashFrame.setVisible(false);
        adminFrame.setVisible(false);
    }

    public void logout() {
        ModernAdminView adminFrame = new ModernAdminView();
        ModernLoginView loginFrame = new ModernLoginView();
        ModernMechanicView mechFrame = new ModernMechanicView();
        ModernCashierView cashFrame = new ModernCashierView();

        loginFrame.setVisible(true);
        mechFrame.setVisible(false);
        cashFrame.setVisible(false);
        adminFrame.setVisible(false);
    }
    
    public boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
}
    public Users findUser( String username){
            UsersDao dao = new UsersDao();
            Users user = dao.findUserRecord(username);
            return user;
    }
    
    public Cars findCar(Integer carId){
            CarsDao dao = new CarsDao();
            Cars car = dao.findCarRecord(carId);
            
            return car;
    }
}
