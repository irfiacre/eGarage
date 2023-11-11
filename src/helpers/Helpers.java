/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import view.AdminVeiw;
import view.CashierVeiw;
import view.MechanicVeiw;
import view.Login;
import java.util.regex.*;

/**
 *
 * @author busyDev
 */
public class Helpers {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =  Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public void navigateToAdminVeiw() {
        AdminVeiw adminFrame = new AdminVeiw();
        Login loginFrame = new Login();
        MechanicVeiw mechFrame = new MechanicVeiw();
        CashierVeiw cashFrame = new CashierVeiw();

        loginFrame.setVisible(false);
        mechFrame.setVisible(false);
        cashFrame.setVisible(false);
        adminFrame.setVisible(true);
    }

    public void navigateToCashierView() {
       AdminVeiw adminFrame = new AdminVeiw();
        Login loginFrame = new Login();
        MechanicVeiw mechFrame = new MechanicVeiw();
        CashierVeiw cashFrame = new CashierVeiw();

        loginFrame.setVisible(false);
        mechFrame.setVisible(false);
        cashFrame.setVisible(true);
        adminFrame.setVisible(false);
    }

    public void navigateToMechanicVeiw() {
        AdminVeiw adminFrame = new AdminVeiw();
        Login loginFrame = new Login();
        MechanicVeiw mechFrame = new MechanicVeiw();
        CashierVeiw cashFrame = new CashierVeiw();

        loginFrame.setVisible(false);
        mechFrame.setVisible(true);
        cashFrame.setVisible(false);
        adminFrame.setVisible(false);
    }

    public void logout() {
        AdminVeiw adminFrame = new AdminVeiw();
        Login loginFrame = new Login();
        MechanicVeiw mechFrame = new MechanicVeiw();
        CashierVeiw cashFrame = new CashierVeiw();

        loginFrame.setVisible(true);
        mechFrame.setVisible(false);
        cashFrame.setVisible(false);
        adminFrame.setVisible(false);
    }
    
    public boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
}
}
