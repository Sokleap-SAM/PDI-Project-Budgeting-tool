public class ReportService {
    private BudgetService budgetService;
    private ExpenseService expenseService;

    public ReportService(BudgetService budgetService, ExpenseService expenseService) {
        this.budgetService = budgetService;
        this.expenseService = expenseService;
    }

    public void generateExpenseReport() {
        System.out.println("Expense Report:");
        expenseService.viewExpenses();
    }

    public void compareBudgetVsExpense() {
        System.out.println("Budget vs Expense Comparison:");
        budgetService.viewBudgetSummary();
        expenseService.viewExpenses();
    }
    
}