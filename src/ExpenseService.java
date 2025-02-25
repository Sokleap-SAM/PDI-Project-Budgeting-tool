import java.util.ArrayList;
import java.util.List;

public class ExpenseService {
    private List<Expense> expenses = new ArrayList<>();

    public void addExpense(String category, double amount) {
        expenses.add(new Expense(category, amount));
        System.out.println("Expense added successfully!");
    }

    public void editExpense(String category, double newAmount) {
        for (Expense expense : expenses) {
            if (expense.getCategory().equals(category)) {
                expense.setAmount(newAmount);
                System.out.println("Expense updated successfully!");
                return;
            }
        }
        System.out.println("Expense category not found!");
    }

    public void deleteExpense(String category) {
        expenses.removeIf(expense -> expense.getCategory().equals(category));
        System.out.println("Expense deleted successfully!");
    }

    public void viewExpenses() {
        System.out.println("Expenses:");
        for (Expense expense : expenses) {
            System.out.println(expense.getCategory() + ": $" + expense.getAmount());
        }
    }
}