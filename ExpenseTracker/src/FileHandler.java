import java.io.*;
import java.util.*;

public class FileHandler {
    public static List<Transaction> loadTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return transactions;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String type = parts[0];
                    String category = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    String date = parts[3];
                    transactions.add(new Transaction(type, category, amount, date));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
        return transactions;
    }

    public static void saveTransactions(List<Transaction> transactions, String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Transaction t : transactions) {
                pw.println(t.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }
}