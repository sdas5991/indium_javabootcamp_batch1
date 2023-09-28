import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BankingAppWithStatistics {
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
                    printStatistics();
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
        accounts.values().forEach(System.out::println);
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

    private static void printStatistics() {
        System.out.println("Banking Statistics:");

        // a] Number of accounts with balance more than 1 lac
        long highBalanceCount = accounts.values().stream()
                .filter(account -> account.getBalance() > 100000)
                .count();
        System.out.println("a] Number of accounts with balance more than 1 lac: " + highBalanceCount);

        // b] Show the number of accounts by account type
        Map<String, Long> accountTypeCounts = accounts.values().stream()
                .collect(Collectors.groupingBy(BankAccount::getAccountType, Collectors.counting()));
        accountTypeCounts.forEach((accountType, count) ->
                System.out.println("b] Account Type: " + accountType + ", Count: " + count));

        // c] Show the number of accounts by account type with sorting
        accounts.values().stream()
                .collect(Collectors.groupingBy(BankAccount::getAccountType, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println("c] Account Type: " + entry.getKey() + ", Count: " + entry.getValue()));

        // d] Show average balance by account type
        Map<String, Double> accountTypeAvgBalances = accounts.values().stream()
                .collect(Collectors.groupingBy(BankAccount::getAccountType, Collectors.averagingDouble(BankAccount::getBalance)));
        accountTypeAvgBalances.forEach((accountType, avgBalance) ->
                System.out.println("d] Account Type: " + accountType + ", Average Balance: " + avgBalance));

        // e] List account IDs whose account name contains a given name
        System.out.print("e] Enter the account name to search: ");
        Scanner scanner = new Scanner(System.in);
        String nameToSearch = scanner.nextLine().toLowerCase();
        List<Integer> accountIds = accounts.entrySet().stream()
                .filter(entry -> entry.getValue().getAccountHolder().toLowerCase().contains(nameToSearch))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("e] Account IDs whose account name contains '" + nameToSearch + "': " + accountIds);
    }

    // The rest of the methods (importData and exportData) remain unchanged
}
