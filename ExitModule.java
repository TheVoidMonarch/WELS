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
