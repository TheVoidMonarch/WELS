/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.eleventhhour.workshopequipmentloansystem;

/**
 *
 * @author Zuan
 */
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        EquipmentManager equipmentManager = new EquipmentManager();
        LoanManager loanManager = new LoanManager(equipmentManager);
        ReportManager reportManager = new ReportManager(equipmentManager, loanManager);

        JOptionPane.showMessageDialog(null,
                "Welcome to the Workshop Equipment Loan System!");

        boolean running = true;

        while (running) {
            String choice = JOptionPane.showInputDialog(null,
                    "WORKSHOP EQUIPMENT LOAN SYSTEM\n\n"
                    + "1. Equipment Management\n"
                    + "2. Loan Equipment\n"
                    + "3. Return Equipment\n"
                    + "4. Search Equipment\n"
                    + "5. Reports\n"
                    + "6. Exit\n\n"
                    + "Enter your choice:");

            if (choice == null) {
                running = !ExitModule.confirmExit();
                continue;
            }

            switch (choice) {
                case "1":
                    equipmentManager.showMenu();
                    break;
                case "2":
                    loanManager.loanEquipment();
                    break;
                case "3":
                    loanManager.returnEquipment();
                    break;
                case "4":
                    equipmentManager.searchEquipment();
                    break;
                case "5":
                    reportManager.showMenu();
                    break;
                case "6":
                    running = !ExitModule.confirmExit();
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid choice. Please enter a number from 1 to 6.");
            }
        }
    }
}