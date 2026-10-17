import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private static int nextLoanId = 1001;

    private int loanId;
    private String borrowerName;
    private String borrowerId;
    private Equipment equipment;
    private int quantity;
    private LocalDate loanDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;
    private double fine;

    public Loan(String borrowerName, String borrowerId, Equipment equipment,
                int quantity, LocalDate loanDate, LocalDate expectedReturnDate) {
        this.loanId = nextLoanId++;
        this.borrowerName = borrowerName;
        this.borrowerId = borrowerId;
        this.equipment = equipment;
        this.quantity = quantity;
        this.loanDate = loanDate;
        this.expectedReturnDate = expectedReturnDate;
        this.fine = 0.0;
    }

    public int getLoanId() { return loanId; }
    public String getBorrowerName() { return borrowerName; }
    public String getBorrowerId() { return borrowerId; }
    public Equipment getEquipment() { return equipment; }
    public int getQuantity() { return quantity; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getExpectedReturnDate() { return expectedReturnDate; }
    public LocalDate getActualReturnDate() { return actualReturnDate; }
    public double getFine() { return fine; }

    public boolean isActive() {
        return actualReturnDate == null;
    }

    public boolean isOverdue() {
        return isActive() && LocalDate.now().isAfter(expectedReturnDate);
    }
    public double getCurrentFine() {
        LocalDate comparisonDate;
        
        if (actualReturnDate != null) {
            comparisonDate = actualReturnDate;
        } else {
            comparisonDate = LocalDate.now();
        }
        
        long overdueDays = ChronoUnit.DAYS.between(expectedReturnDate, comparisonDate);
        if (overdueDays > 0) {
            return overdueDays * 2.00 * quantity;
        }
        return 0.00;
    }

    public void completeReturn(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
        long overdueDays = ChronoUnit.DAYS.between(expectedReturnDate, actualReturnDate);
        if (overdueDays > 0) {
            fine = overdueDays * 2.00 * quantity;
        }
    }

    @Override
    public String toString() {
        String status = isActive() ? "Active" : "Returned";
        return "Loan ID: " + loanId
                + " | Equipment: " + equipment.getName()
                + " | Borrower: " + borrowerName
                + " | Quantity: " + quantity
                + " | Due: " + expectedReturnDate
                + " | Status: " + status
                + " | Fine: RM" + String.format("%.2f", getCurrentFine());
    }
}