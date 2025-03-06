// import java.io.*;
// import java.util.ArrayList;
// import java.util.List;

// public class DataManagement {
//     private String USERS_FILE = "data/users.txt";
//     private String BUDGETS_FILE = "data/budgets.txt";
//     private String EXPENSES_FILE = "data/expenses.txt";

//     public void saveUsers(List<User> users) {
//         try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
//             for (User user : users) {
//                 writer.write(user.getUserName() + "," + user.getPassword());
//                 writer.newLine();
//             }
//         } catch (IOException e) {
//             System.out.println("Error saving users: " + e.getMessage());
//         }
//     }

//     public List<User> loadUsers() {
//         List<User> users = new ArrayList<>();
//         try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 String[] parts = line.split(",");
//                 users.add(new User(parts[0], parts[1]));
//             }
//         } catch (IOException e) {
//             System.out.println("Error loading users: " + e.getMessage());
//         }
//         return users;
//     }

//     public void saveBudgets(List<Budget> budgets) {
//         try (BufferedWriter writer = new BufferedWriter(new FileWriter(BUDGETS_FILE))) {
//             for (Budget budget : budgets) {
//                 writer.write(budget.getCategory() + "," + budget.getAmount());
//                 writer.newLine();
//             }
//         } catch (IOException e) {
//             System.out.println("Error saving budgets: " + e.getMessage());
//         }
//     }

//     public List<Budget> loadBudgets() {
//         List<Budget> budgets = new ArrayList<>();
//         try (BufferedReader reader = new BufferedReader(new FileReader(BUDGETS_FILE))) {
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 String[] parts = line.split(",");
//                 budgets.add(new Budget(parts[0], Double.parseDouble(parts[1])));
//             }
//         } catch (IOException e) {
//             System.out.println("Error loading budgets: " + e.getMessage());
//         }
//         return budgets;
//     }

//     public void saveExpenses(List<Expense> expenses) {
//         try (BufferedWriter writer = new BufferedWriter(new FileWriter(EXPENSES_FILE))) {
//             for (Expense expense : expenses) {
//                 writer.write(expense.getCategory() + "," + expense.getAmount());
//                 writer.newLine();
//             }
//         } catch (IOException e) {
//             System.out.println("Error saving expenses: " + e.getMessage());
//         }
//     }

//     public List<Expense> loadExpenses() {
//         List<Expense> expenses = new ArrayList<>();
//         try (BufferedReader reader = new BufferedReader(new FileReader(EXPENSES_FILE))) {
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 String[] parts = line.split(",");
//                 expenses.add(new Expense(parts[0], Double.parseDouble(parts[1])));
//             }
//         } catch (IOException e) {
//             System.out.println("Error loading expenses: " + e.getMessage());
//         }
//         return expenses;
//     }
// }
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManagement {
    private static final String URL = "jdbc:mysql://localhost:3306/myDB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "LeapSQL@123";

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "username VARCHAR(50),"
                + "password VARCHAR(50)"
                + ")";
        try (Connection connect = getConnection();
                Statement statement = connect.createStatement()) {
            statement.execute(query);
            System.out.println("Users table created or already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating users table: " + e.getMessage());
        }
    }

    void saveUser(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection connect = getConnection();
                PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            System.out.println("User saved successfully!");
        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    void updateUserPassword(String username, String newPassword) {
        String query = "UPDATE users SET password = ? Where username = ?";
        try (Connection connect = getConnection();
                PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, newPassword);
            statement.setString(2, username);
            statement.executeUpdate();
            System.out.println("Update password successfully!");
        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    Map <String, User> loadUser() {
        Map <String, User> users = new HashMap<>();
        String query = "SELECT * FROM users";
        try (Connection connect = getConnection();
                PreparedStatement statement = connect.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                users.put(resultSet.getString("username"), 
                new User(resultSet.getString("username"),resultSet.getString("password")));
            }
        } catch (SQLException e) {
            System.err.println("Error loading user: " + e.getMessage());
        }
        return users;
    }

    void createBudgetTable() {
        String query = "CREATE TABLE IF NOT EXISTS budgets ("
                + "Username VARCHAR(50),"
                + "Salary_Income DECIMAL(5,2) DEFAULT 0,"
                + "Freelance_Income DECIMAL(5,2) DEFAULT 0,"
                + "Investment_Income DECIMAL(5,2) DEFAULT 0,"
                + "Rental_Income DECIMAL(5,2) DEFAULT 0,"
                + "Bonuses_Income DECIMAL(5,2) DEFAULT 0,"
                + "Government_Benefits DECIMAL(5,2) DEFAULT 0,"
                + "Saving DECIMAL(5,2) DEFAULT 0,"
                + "Other_Incomes DECIMAL(5,2) DEFAULT 0"
                + ")";
        try (Connection connect = getConnection();
                Statement statement = connect.createStatement()) {
            statement.execute(query);
            System.out.println("Users table created or already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating users table: " + e.getMessage());
        }
    }

    void addBudget(String username){
        String query = "INSERT INTO budgets (username) VALUES (?)";
        try (Connection connect = getConnection();
                PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, username);
            statement.executeUpdate();
            System.out.println("Budget saved successfully!");
        } catch (SQLException e) {
            System.err.println("Error saving budget: " + e.getMessage());
        }
    }

    Map <String,Budget> loadBudget(String username) {
        Map <String,Budget> budgets = new HashMap<>();
        String query = "SELECT * FROM budgets WHERE username = ?";
        try (Connection connect = getConnection();
                PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                budgets.put("Salary_Income", new Budget("Salary_Income",resultSet.getDouble("Salary_Income")));
                budgets.put("Freelance_Income", new Budget("Freelance_Income", resultSet.getDouble("Freelance_Income")));
                budgets.put("Investment_Income",  new Budget("Investment_Income", resultSet.getDouble("Investment_Income")));
                budgets.put("Rental_Income", new Budget("Rental_Income", resultSet.getDouble("Rental_Income")));
                budgets.put("Bonuses_Income", new Budget("Bonuses_Income", resultSet.getDouble("Bonuses_Income")));
                budgets.put("Government_Benefits", new Budget("Government_Benefits", resultSet.getDouble("Government_Benefits")));
                budgets.put("Saving", new Budget("Saving", resultSet.getDouble("Saving")));
                budgets.put("Other_Incomes",  new Budget("Other_Incomes", resultSet.getDouble("Other_Incomes")));
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error loading user: " + e.getMessage());
        }
        return budgets;
    }

    void updateBudget(String budgetCategory, double amount, String username) {
        String query = "UPDATE budgets SET ? = ? Where username = ?";
        try (Connection connect = getConnection();
                PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, budgetCategory);
            statement.setDouble(2, amount);
            statement.setString(3, username);
            statement.executeUpdate();
            System.out.println("Update budget successfully!");
        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    void createExpenseTable() {
        String query = "CREATE TABLE IF NOT EXISTS expenses ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "Housing DECIMAL(5,2),"
                + "Utilities DECIMAL(5,2),"
                + "Groceries DECIMAL(5,2),"
                + "Transportation DECIMAL(5,2),"
                + "Health_Care DECIMAL(5,2),"
                + "Personal_Care DECIMAL(5,2),"
                + "Education DECIMAL(5,2),"
                + "Entertainment DECIMAL(5,2),"
                + "Shopping DECIMAL(5,2),"
                + "Subscriptions DECIMAL(5,2),"
                + "Gifts DECIMAL(5,2),"
                + "Pet_Care DECIMAL(5,2),"
                + "Other_Expenses DECIMAL(5,2),"
                + ")";
        try (Connection connect = getConnection();
                Statement statement = connect.createStatement()) {
            statement.execute(query);
            System.out.println("Users table created or already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating users table: " + e.getMessage());
        }
    }

    void updateExpense(String expenseCategory, double amount, String username) {
        String query = "UPDATE expenses SET ? = ? Where username = ?";
        try (Connection connect = getConnection();
                PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, expenseCategory);
            statement.setDouble(2, amount);
            statement.setString(3, username);
            statement.executeUpdate();
            System.out.println("Update expense successfully!");
        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    void loadExpense(String username) {
        String query = "SELECT * FROM expenses WHERE username = ?";
        try (Connection connect = getConnection();
                PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                //modify
                System.out.println("User found:");
                System.out.println("Username: " + resultSet.getString("username"));
                System.out.println("Password: " + resultSet.getString("password"));
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error loading user: " + e.getMessage());
        }
    }
}