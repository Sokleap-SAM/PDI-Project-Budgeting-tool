import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthService authService = new AuthService();
        BudgetService budgetService = new BudgetService();
        ExpenseService expenseService = new ExpenseService();
        ReportService reportService = new ReportService(budgetService, expenseService);

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    authService.register(regUsername, regPassword);
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    if (authService.login(loginUsername, loginPassword)) {
                        boolean loggedIn = true;
                        while (loggedIn) {
                            System.out.println("\n1. Add Budget\n2. Edit Budget\n3. View Budget Summary\n4. Add Expense\n5. Edit Expense\n6. Delete Expense\n7. Generate Expense Report\n8. Compare Budget vs Expense\n9. Logout");
                            System.out.print("Choose an option: ");
                            int userChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (userChoice) {
                                case 1:
                                    System.out.print("Enter category: ");
                                    String budgetCategory = scanner.nextLine();
                                    System.out.print("Enter amount: ");
                                    double budgetAmount = scanner.nextDouble();
                                    budgetService.addBudget(budgetCategory, budgetAmount);
                                    break;

                                case 2:
                                    System.out.print("Enter category: ");
                                    String editBudgetCategory = scanner.nextLine();
                                    System.out.print("Enter new amount: ");
                                    double newBudgetAmount = scanner.nextDouble();
                                    budgetService.editBudget(editBudgetCategory, newBudgetAmount);
                                    break;

                                case 3:
                                    budgetService.viewBudgetSummary();
                                    break;

                                case 4:
                                    System.out.print("Enter category: ");
                                    String expenseCategory = scanner.nextLine();
                                    System.out.print("Enter amount: ");
                                    double expenseAmount = scanner.nextDouble();
                                    expenseService.addExpense(expenseCategory, expenseAmount);
                                    break;

                                case 5:
                                    System.out.print("Enter category: ");
                                    String editExpenseCategory = scanner.nextLine();
                                    System.out.print("Enter new amount: ");
                                    double newExpenseAmount = scanner.nextDouble();
                                    expenseService.editExpense(editExpenseCategory, newExpenseAmount);
                                    break;

                                case 6:
                                    System.out.print("Enter category: ");
                                    String deleteExpenseCategory = scanner.nextLine();
                                    expenseService.deleteExpense(deleteExpenseCategory);
                                    break;

                                case 7:
                                    reportService.generateExpenseReport();
                                    break;

                                case 8:
                                    reportService.compareBudgetVsExpense();
                                    break;

                                case 9:
                                    loggedIn = false;
                                    System.out.println("Logged out successfully!");
                                    break;

                                default:
                                    System.out.println("Invalid option!");
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}