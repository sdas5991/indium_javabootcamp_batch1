import java.util.Scanner;

public class BankingApp {
    private static final int MAX_ACCOUNTS = 100;
    private static BankAccount[] accounts = new BankAccount[MAX_ACCOUNTS];
    private static int accountCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Banking App Menu:");
            System.out.println("1] Add Account");
            System.out.println("2] View All Accounts");
            System.out.println("3] View Account");
            System.out.println("4] Update Account");
            System.out.println("5] Delete Account");
            System.out.println("6] Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
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
                    System.out.println("Exiting the Banking App.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addAccount(Scanner scanner) {
        if (accountCount < MAX_ACCOUNTS) {
            System.out.print("Enter account holder's name: ");
            String name = scanner.nextLine();
            System.out.print("Enter initial balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            BankAccount account = new BankAccount(name, balance);
            accounts[accountCount] = account;
            accountCount++;

            System.out.println("Account added successfully.");
        } else {
            System.out.println("Maximum number of accounts reached. Cannot add more.");
        }
    }

    private static void viewAllAccounts() {
        System.out.println("All Accounts:");
        for (int i = 0; i < accountCount; i++) {
            System.out.println(accounts[i]);
        }
    }

    private static void viewAccount(Scanner scanner) {
        System.out.print("Enter account number to view: ");
        int accountNumber = scanner.nextInt();

        if (accountNumber >= 1 && accountNumber <= accountCount) {
            System.out.println("Account Details:");
            System.out.println(accounts[accountNumber - 1]);
        } else {
            System.out.println("Invalid account number.");
        }
    }

    private static void updateAccount(Scanner scanner) {
        System.out.print("Enter account number to update: ");
        int accountNumber = scanner.nextInt();

        if (accountNumber >= 1 && accountNumber <= accountCount) {
            BankAccount account = accounts[accountNumber - 1];
            System.out.println("Current Account Details:");
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
            System.out.println("Invalid account number.");
        }
    }

    private static void deleteAccount(Scanner scanner) {
        System.out.print("Enter account number to delete: ");
        int accountNumber = scanner.nextInt();

        if (accountNumber >= 1 && accountNumber <= accountCount) {
            for (int i = accountNumber - 1; i < accountCount - 1; i++) {
                accounts[i] = accounts[i + 1];
            }
            accountCount--;

            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Invalid account number.");
        }
    }
}

class BankAccount {
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

    @Override
    public String toString() {
        return "Account Holder: " + accountHolder + ", Balance: $" + balance;
    }
}
