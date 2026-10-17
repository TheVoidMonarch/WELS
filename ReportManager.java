import javax.swing.JOptionPane;

public class ReportManager {
    private EquipmentManager equipmentManager;
    private LoanManager loanManager;

    public ReportManager(EquipmentManager equipmentManager, LoanManager loanManager) {
        this.equipmentManager = equipmentManager;
        this.loanManager = loanManager;
    }

    public void showMenu() {
        boolean back = false;
        while (!back) {
            String choice = JOptionPane.showInputDialog(null,
                    "REPORTS\n\n"
                    + "1. Display all equipment\n"
                    + "2. Display available equipment\n"
                    + "3. Display borrowed equipment\n"
                    + "4. Display overdue loans\n"
                    + "5. Display total collected fines\n"
                    + "6. Return to main menu\n\n"
                    + "Enter your choice:");

            if (choice == null || choice.equals("6")) {
                back = true;
            } else {
                switch (choice) {
                    case "1": displayAllEquipment(); break;
                    case "2": displayAvailableEquipment(); break;
                    case "3": displayBorrowedEquipment(); break;
                    case "4": displayOverdueLoans(); break;
                    case "5": displayTotalFines(); break;
                    default: JOptionPane.showMessageDialog(null, "Invalid choice.");
                }
            }
        }
    }

    private void displayAllEquipment() {
        StringBuilder output = new StringBuilder("ALL EQUIPMENT\n\n");
        for (Equipment equipment : equipmentManager.getEquipmentList()) {
            output.append(equipment).append("\n");
        }
        JOptionPane.showMessageDialog(null, output.toString());
    }

    private void displayAvailableEquipment() {
        StringBuilder output = new StringBuilder("AVAILABLE EQUIPMENT\n\n");
        for (Equipment equipment : equipmentManager.getEquipmentList()) {
            if (equipment.getAvailableQuantity() > 0) {
                output.append(equipment).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, output.toString());
    }

    private void displayBorrowedEquipment() {
        StringBuilder output = new StringBuilder("BORROWED EQUIPMENT\n\n");
        for (Loan loan : loanManager.getLoanList()) {
            if (loan.isActive()) output.append(loan).append("\n");
        }
        JOptionPane.showMessageDialog(null, output.toString());
    }

    private void displayOverdueLoans() {
        StringBuilder output = new StringBuilder("OVERDUE LOANS\n\n");
        for (Loan loan : loanManager.getLoanList()) {
            if (loan.isOverdue()) output.append(loan).append("\n");
        }
        JOptionPane.showMessageDialog(null, output.toString());
    }

    private void displayTotalFines() {
        double total = 0.0;
        for (Loan loan : loanManager.getLoanList()) {
            total += loan.getFine();
        }
        JOptionPane.showMessageDialog(null,
                "Total collected fines: RM" + String.format("%.2f", total));
    }
}
