import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    public String budgetMenu() {
        int budgetOption = 0;
        String budgetCategory = null;
        while (budgetOption == 0) {
            System.out.print(
                    "Category:\n1. Salary\n2. Freelance Income\n3. Investment Income\n4. Rental Income\n5. Bonuses\n6. Government Benefits\n7. Saving\n8. Other Incomes\nChoice: ");
            budgetOption = scanner.nextInt();
            scanner.nextLine();
            switch (budgetOption) {
                case 1:
                    budgetCategory = "Salary";
                    break;
                case 2:
                    budgetCategory = "Freelance_Income";
                    break;
                case 3:
                    budgetCategory = "Investment_Income";
                    break;
                case 4:
                    budgetCategory = "Rental_Income";
                    break;
                case 5:
                    budgetCategory = "Bonuses";
                    break;
                case 6:
                    budgetCategory = "Government_Benefits";
                    break;
                case 7:
                    budgetCategory = "Saving";
                    break;
                case 8:
                    budgetCategory = "Other_Incomes";
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        return budgetCategory;
    }

    public String expenseMenu() {
        int expenseOption = 0;
        String expenseCategory = null;
        while (expenseOption == 0) {
            System.out.print(
                    "Category:\n1. Housing\n2. Utilities\n3. Groceries\n4. Transportation\n5. Health Care\n6. Personal Care\n7. Education\n8. Entertainment\n9. Shopping\n10. Subscriptions\n11. Gifts\n12. Pet Care\n13. Other Expenses");
            expenseOption = scanner.nextInt();
            scanner.nextLine();
            switch (expenseOption) {
                case 1:
                    expenseCategory = "Housing";
                    break;
                case 2:
                    expenseCategory = "Utilities";
                    break;
                case 3:
                    expenseCategory = "Groceries";
                    break;
                case 4:
                    expenseCategory = "Transportation";
                    break;
                case 5:
                    expenseCategory = "Health_Care";
                    break;
                case 6:
                    expenseCategory = "Personal_Care";
                    break;
                case 7:
                    expenseCategory = "Education";
                    break;
                case 8:
                    expenseCategory = "Entertainment";
                    break;
                case 9:
                    expenseCategory = "Shopping";
                    break;
                case 10:
                    expenseCategory = "Subscription";
                    break;
                case 11:
                    expenseCategory = "Gifts";
                    break;
                case 12:
                    expenseCategory = "Pet_Care";
                    break;
                case 13:
                    expenseCategory = "Other_Expenses";
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        return expenseCategory;
    }
}
