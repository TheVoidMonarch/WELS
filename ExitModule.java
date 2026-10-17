/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eleventhhour.workshopequipmentloansystem;

/**
 *
 * @author Administrator
 */
import javax.swing.JOptionPane;

public class ExitModule {
    public static boolean confirmExit() {
        int choice = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to exit?",
                "Exit System",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null,
                    "Thank you for using the Workshop Equipment Loan System.");
            return true;
        }

        return false;
    }
}