import java.util.*;
import java.io.*;

public class ExpenseTracker {
    private static final String FILE_PATH = "data/transactions.txt";
    private static List<Transaction> transactions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ensureDataDirectoryExists();
        transactions = FileHandler.loadTransactions(FILE_PATH);
        boolean exit = false;
        while (!exit) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Monthly Summary");
            System.out.println("4. Save and Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addTransaction("income");
                    break;
                case "2":
                    addTransaction("expense");
                    break;
                case "3":
                    viewMonthlySummary();
                    break;
                case "4":
                    FileHandler.saveTransactions(transactions, FILE_PATH);
                    System.out.println("Transactions saved. Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void ensureDataDirectoryExists() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
    }

    private static void addTransaction(String type) {
        System.out.print("Enter category (e.g., salary, food, rent): ");
        String category = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter date (YYYY-MM): ");
        String date = scanner.nextLine();
        transactions.add(new Transaction(type, category, amount, date));
        System.out.println("Transaction added successfully.");
    }

    private static void viewMonthlySummary() {
        System.out.print("Enter month to summarize (YYYY-MM): ");
        String month = scanner.nextLine();
        double totalIncome = 0;
        double totalExpense = 0;
        for (Transaction t : transactions) {
            if (t.getDate().equals(month)) {
                if (t.getType().equalsIgnoreCase("income")) {
                    totalIncome += t.getAmount();
                } else if (t.getType().equalsIgnoreCase("expense")) {
                    totalExpense += t.getAmount();
                }
            }
        }
        System.out.println("Summary for " + month + ":");
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expenses: " + totalExpense);
        System.out.println("Net Savings: " + (totalIncome - totalExpense));
    }
}
