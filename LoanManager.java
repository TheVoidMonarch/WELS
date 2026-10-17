import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.ArrayList;

public class LoanManager {
    private ArrayList<Loan> loanList = new ArrayList<>();
    private EquipmentManager equipmentManager;

    public LoanManager(EquipmentManager equipmentManager) {
        this.equipmentManager = equipmentManager;
    }

    public ArrayList<Loan> getLoanList() {
        return loanList;
    }

    public void loanEquipment() {
        String equipmentId = InputValidator.getRequiredText("Enter equipment ID:");
        if (equipmentId == null) return;
        Equipment equipment = equipmentManager.findById(equipmentId);
        if (equipment == null) {
            JOptionPane.showMessageDialog(null, "Equipment not found.");
            return;
        }

        int quantity = InputValidator.getPositiveInteger("Enter quantity to loan:");
        if (quantity == -1) return;
        if (quantity > equipment.getAvailableQuantity()) {
            JOptionPane.showMessageDialog(null,
                    "Loan unsuccessful: insufficient equipment available.");
            return;
        }

        String borrowerName = InputValidator.getRequiredText("Enter borrower name:");
        if (borrowerName == null) return;
        String borrowerId = InputValidator.getRequiredText("Enter student/staff ID:");
        if (borrowerId == null) return;
        LocalDate loanDate = InputValidator.getDate("Enter loan date");
        if (loanDate == null) return;
        LocalDate expectedDate = InputValidator.getDate("Enter expected return date");
        if (expectedDate == null) return;
        if (expectedDate.isBefore(loanDate)) {
            JOptionPane.showMessageDialog(null,
                    "Expected return date cannot be before the loan date.");
            return;
        }

        equipment.borrow(quantity);
        loanList.add(new Loan(borrowerName, borrowerId, equipment,
                quantity, loanDate, expectedDate));
        JOptionPane.showMessageDialog(null, "Equipment loan recorded successfully.");
    }

    public void returnEquipment() {
        int loanId = InputValidator.getPositiveInteger("Enter loan ID:");
        if (loanId == -1) return;
        Loan loan = findActiveLoan(loanId);
        if (loan == null) {
            JOptionPane.showMessageDialog(null, "Active loan not found.");
            return;
        }

        LocalDate actualDate = InputValidator.getDate("Enter actual return date");
        if (actualDate == null) return;
        if (actualDate.isBefore(loan.getLoanDate())) {
            JOptionPane.showMessageDialog(null,
                    "Return date cannot be before the loan date.");
            return;
        }

        loan.completeReturn(actualDate);
        loan.getEquipment().returnItems(loan.getQuantity());
        JOptionPane.showMessageDialog(null,
                "Equipment returned successfully.\nFine: RM"
                + String.format("%.2f", loan.getFine()));
    }

    public Loan findActiveLoan(int loanId) {
        for (Loan loan : loanList) {
            if (loan.getLoanId() == loanId && loan.isActive()) return loan;
        }
        return null;
    }
}
