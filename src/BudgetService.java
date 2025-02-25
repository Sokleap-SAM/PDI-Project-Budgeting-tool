import java.util.ArrayList;
import java.util.List;

public class BudgetService {
    private List<Budget> budgets = new ArrayList<>();

    public void addBudget(String category, double amount) {
        budgets.add(new Budget(category, amount));
        System.out.println("Budget added successfully!");
    }

    public void editBudget(String category, double newAmount) {
        for (Budget budget : budgets) {
            if (budget.getCategory().equals(category)) {
                budget.setAmount(newAmount);
                System.out.println("Budget updated successfully!");
                return;
            }
        }
        System.out.println("Budget category not found!");
    }

    public void viewBudgetSummary() {
        System.out.println("Budget Summary:");
        for (Budget budget : budgets) {
            System.out.println(budget.getCategory() + ": $" + budget.getAmount());
        }
    }
}