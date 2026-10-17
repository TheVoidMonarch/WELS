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
import java.util.ArrayList;

public class EquipmentManager {
    private ArrayList<Equipment> equipmentList = new ArrayList<>();

    public EquipmentManager() {
        // Sample records for testing. These can be removed later.
        equipmentList.add(new Equipment("E001", "Electric Drill", "Power Tool", 5, "Good"));
        equipmentList.add(new Equipment("E002", "Safety Helmet", "Safety", 10, "Good"));
    }

    public ArrayList<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void showMenu() {
        boolean back = false;
        while (!back) {
            String choice = JOptionPane.showInputDialog(null,
                    "EQUIPMENT MANAGEMENT\n\n"
                    + "1. Add equipment\n"
                    + "2. View all equipment\n"
                    + "3. Update equipment\n"
                    + "4. Remove equipment\n"
                    + "5. Return to main menu\n\n"
                    + "Enter your choice:");

            if (choice == null || choice.equals("5")) {
                back = true;
            } else {
                switch (choice) {
                    case "1": addEquipment(); break;
                    case "2": viewAllEquipment(); break;
                    case "3": updateEquipment(); break;
                    case "4": removeEquipment(); break;
                    default: JOptionPane.showMessageDialog(null, "Invalid choice.");
                }
            }
        }
    }

    public void addEquipment() {
        String id = InputValidator.getRequiredText("Enter equipment ID:");
        if (id == null) return;
        if (findById(id) != null) {
            JOptionPane.showMessageDialog(null, "That equipment ID already exists.");
            return;
        }

        String name = InputValidator.getRequiredText("Enter equipment name:");
        if (name == null) return;
        String category = InputValidator.getRequiredText("Enter category:");
        if (category == null) return;
        int quantity = InputValidator.getPositiveInteger("Enter total quantity:");
        if (quantity == -1) return;
        String condition = InputValidator.getRequiredText("Enter condition:");
        if (condition == null) return;

        equipmentList.add(new Equipment(id, name, category, quantity, condition));
        JOptionPane.showMessageDialog(null, "Equipment added successfully.");
    }

    public void viewAllEquipment() {
        if (equipmentList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No equipment records found.");
            return;
        }

        StringBuilder output = new StringBuilder();
        output.append(String.format("%-8s %-20s %-15s %4s %4s %-15s %-15s%n",
                "ID", "Name", "Category", "Total", "Avail.", "Condition", "Status"));
        output.append("-------------------------------------------------------------------------------\n");
        for (Equipment equipment : equipmentList) {
            output.append(equipment).append("\n");
        }
        JOptionPane.showMessageDialog(null, output.toString());
    }

    public void updateEquipment() {
        String id = InputValidator.getRequiredText("Enter equipment ID to update:");
        if (id == null) return;
        Equipment equipment = findById(id);
        if (equipment == null) {
            JOptionPane.showMessageDialog(null, "Equipment not found.");
            return;
        }

        String name = InputValidator.getRequiredText("Enter new name:");
        if (name == null) return;
        String category = InputValidator.getRequiredText("Enter new category:");
        if (category == null) return;
        String condition = InputValidator.getRequiredText("Enter new condition:");
        if (condition == null) return;

        equipment.setName(name);
        equipment.setCategory(category);
        equipment.setCondition(condition);
        JOptionPane.showMessageDialog(null, "Equipment updated successfully.");
    }

    public void removeEquipment() {
        String id = InputValidator.getRequiredText("Enter equipment ID to remove:");
        if (id == null) return;
        Equipment equipment = findById(id);
        if (equipment == null) {
            JOptionPane.showMessageDialog(null, "Equipment not found.");
            return;
        }
        if (equipment.getAvailableQuantity() != equipment.getTotalQuantity()) {
            JOptionPane.showMessageDialog(null,
                    "Cannot remove equipment while some units are borrowed.");
            return;
        }

        equipmentList.remove(equipment);
        JOptionPane.showMessageDialog(null, "Equipment removed successfully.");
    }

    public void searchEquipment() {
        String keyword = InputValidator.getRequiredText(
                "Enter equipment ID, name, or category to search:");
        if (keyword == null) return;
        keyword = keyword.toLowerCase();

        StringBuilder result = new StringBuilder();
        for (Equipment equipment : equipmentList) {
            if (equipment.getId().toLowerCase().contains(keyword)
                    || equipment.getName().toLowerCase().contains(keyword)
                    || equipment.getCategory().toLowerCase().contains(keyword)) {
                result.append(equipment).append("\n");
            }
        }

        if (result.length() == 0) {
            JOptionPane.showMessageDialog(null, "No matching equipment found.");
        } else {
            JOptionPane.showMessageDialog(null, result.toString());
        }
    }

    public Equipment findById(String id) {
        for (Equipment equipment : equipmentList) {
            if (equipment.getId().equalsIgnoreCase(id)) return equipment;
        }
        return null;
    }
}