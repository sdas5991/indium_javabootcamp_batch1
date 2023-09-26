import java.io.*;
import java.util.*;

public class BankingAppWithImportExport {
    private static Map<Integer, BankAccount> accounts = new HashMap<>();
    private static int accountIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Banking App Menu:");
            System.out.println("1] Add Account");
            System.out.println("2] View All Accounts");
            System.out.println("3] View Account");
            System.out.println("4] Update Account");
            System.out.println("5] Delete Account");
            System.out.println("6] Print Statistics");
            System.out.println("7] Import");
            System.out.println("8] Export");
            System.out.println("9] Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addAccount(scanner);
                    break;
                case 2:
                    viewAllAccounts();
                    break;
                case 3:
                    viewAccount(scanner);
                    break;
                case 4:
                    updateAccount(scanner);
                    break;
                case 5:
                    deleteAccount(scanner);
                    break;
                case 6:
                    printStatistics(scanner);
                    break;
                case 7:
                    importData(scanner);
                    break;
                case 8:
                    exportData();
                    break;
                case 9:
                    System.out.println("Exiting the Banking App.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addAccount(Scanner scanner) {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Account holder's name cannot be empty.");
            return;
        }

        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        BankAccount account = new BankAccount(name, balance);
        accounts.put(accountIdCounter, account);
        accountIdCounter++;

        System.out.println("Account added successfully. Account ID: " + (accountIdCounter - 1));
    }

    private static void viewAllAccounts() {
        System.out.println("All Accounts:");
        for (Map.Entry<Integer, BankAccount> entry : accounts.entrySet()) {
            System.out.println("Account ID: " + entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    private static void viewAccount(Scanner scanner) {
        System.out.print("Enter account ID to view: ");
        int accountId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        BankAccount account = accounts.get(accountId);

        if (account != null) {
            System.out.println("Account Details (ID: " + accountId + "):");
            System.out.println(account);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void updateAccount(Scanner scanner) {
        System.out.print("Enter account ID to update: ");
        int accountId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        BankAccount account = accounts.get(accountId);

        if (account != null) {
            System.out.println("Current Account Details (ID: " + accountId + "):");
            System.out.println(account);

            System.out.print("Enter new account holder's name (press Enter to keep it unchanged): ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                account.setAccountHolder(newName);
            }

            System.out.print("Enter new balance (press Enter to keep it unchanged): ");
            String newBalanceStr = scanner.nextLine().trim();
            if (!newBalanceStr.isEmpty()) {
                try {
                    double newBalance = Double.parseDouble(newBalanceStr);
                    account.setBalance(newBalance);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid balance input. Balance remains unchanged.");
                }
            }

            System.out.println("Account updated successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void deleteAccount(Scanner scanner) {
        System.out.print("Enter account ID to delete: ");
        int accountId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        BankAccount account = accounts.get(accountId);

        if (account != null) {
            accounts.remove(accountId);
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void printStatistics(Scanner scanner) {
        System.out.println("Banking Statistics Menu:");
        System.out.println("a] No of accounts which has balance more than 1 lac");
        System.out.println("b] Show no of account by account type");
        System.out.println("c] Show no of accounts by account type with sorting");
        System.out.println("d] Show avg balance by account type");
        System.out.println("e] List account IDs whose account name contains given name");
        System.out.println("f] Back to Banking App Menu");

        System.out.print("Enter your choice: ");
        String choice = scanner.next();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case "a":
                countAccountsWithHighBalance();
                break;
            case "b":
                countAccountsByType();
                break;
            case "c":
                countAccountsByTypeWithSorting();
                break;
            case "d":
                calculateAverageBalanceByType();
                break;
            case "e":
                listAccountIdsByName(scanner);
                break;
            case "f":
                return; // Return to the previous menu
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void countAccountsWithHighBalance() {
        long count = accounts.values()
                .stream()
                .filter(account -> account.getBalance() > 100000)
                .count();
        System.out.println("Number of accounts with balance more than 1 lac: " + count);
    }

    private static void countAccountsByType() {
        Map<String, Integer> accountTypeCounts = new HashMap<>();
        for (BankAccount account : accounts.values()) {
            String accountType = account.getAccountType();
            accountTypeCounts.put(accountType, accountTypeCounts.getOrDefault(accountType, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : accountTypeCounts.entrySet()) {
            System.out.println("Account Type: " + entry.getKey() + ", Count: " + entry.getValue());
        }
    }

    private static void countAccountsByTypeWithSorting() {
        Map<String, Integer> accountTypeCounts = new HashMap<>();
        for (BankAccount account : accounts.values()) {
            String accountType = account.getAccountType();
            accountTypeCounts.put(accountType, accountTypeCounts.getOrDefault(accountType, 0) + 1);
        }
        accountTypeCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println("Account Type: " + entry.getKey() + ", Count: " + entry.getValue()));
    }

    private static void calculateAverageBalanceByType() {
        Map<String, Double> accountTypeBalances = new HashMap<>();
        Map<String, Integer> accountTypeCounts = new HashMap<>();

        for (BankAccount account : accounts.values()) {
            String accountType = account.getAccountType();
            double balance = account.getBalance();

            accountTypeBalances.put(accountType, accountTypeBalances.getOrDefault(accountType, 0.0) + balance);
            accountTypeCounts.put(accountType, accountTypeCounts.getOrDefault(accountType, 0) + 1);
        }

        for (Map.Entry<String, Double> entry : accountTypeBalances.entrySet()) {
            String accountType = entry.getKey();
            double totalBalance = entry.getValue();
            int count = accountTypeCounts.get(accountType);
            double averageBalance = totalBalance / count;
            System.out.println("Account Type: " + accountType + ", Average Balance: " + averageBalance);
        }
    }

    private static void listAccountIdsByName(Scanner scanner) {
        System.out.print("Enter the account name to search: ");
        String nameToSearch = scanner.nextLine();
        System.out.println("Account IDs whose account name contains '" + nameToSearch + "':");

        for (Map.Entry<Integer, BankAccount> entry : accounts.entrySet()) {
            if (entry.getValue().getAccountHolder().toLowerCase().contains(nameToSearch.toLowerCase())) {
                System.out.println("Account ID: " + entry.getKey());
            }
        }
    }

    private static void importData(Scanner scanner) {
        System.out.print("Enter the filename to import data from: ");
        String fileName = scanner.nextLine();

        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            accounts = (Map<Integer, BankAccount>) ois.readObject();
            System.out.println("Data imported successfully.");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error importing data: " + e.getMessage());
        }
    }

    private static void exportData() {
        System.out.print("Enter the filename to export data to: ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(accounts);
            System.out.println("Data exported successfully.");

        } catch (IOException e) {
            System.out.println("Error exporting data: " + e.getMessage());
        }
    }
}

class BankAccount implements Serializable {
    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        if (balance >= 100000) {
            return "High Balance";
        } else {
            return "Low Balance";
        }
    }

    @Override
    public String toString() {
        return "Account Holder: " + accountHolder + ", Balance: $" + balance;
    }
}
