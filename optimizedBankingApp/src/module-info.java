import java.util.*;

class OptimizedBankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Banking App Menu:");
        System.out.println("1] ArrayList");
        System.out.println("2] LinkedList");
        System.out.println("3] HashSet");
        System.out.println("4] TreeSet");
        System.out.println("5] HashMap");
        System.out.println("6] TreeMap");
        System.out.println("7] Exit");

        System.out.print("Choose a collection type: ");
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                performBankingOperations(new ArrayList<>());
                break;
            case 2:
                performBankingOperations(new LinkedList<>());
                break;
            case 3:
                performBankingOperations(new HashSet<>());
                break;
            case 4:
                performBankingOperations(new TreeSet<>());
                break;
            case 5:
                performBankingOperations(new HashMap<>());
                break;
            case 6:
                performBankingOperations(new TreeMap<>());
                break;
            case 7:
                System.out.println("Exiting the Banking App.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void performBankingOperations(Collection<BankAccount> accounts) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Banking Operations Menu:");
            System.out.println("1] Add Account");
            System.out.println("2] View All Accounts");
            System.out.println("3] View Account");
            System.out.println("4] Update Account");
            System.out.println("5] Delete Account");
            System.out.println("6] Back to Collection Type Menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addAccount(accounts, scanner);
                    break;
                case 2:
                    viewAllAccounts(accounts);
                    break;
                case 3:
                    viewAccount(accounts, scanner);
                    break;
                case 4:
                    updateAccount(accounts, scanner);
                    break;
                case 5:
                    deleteAccount(accounts, scanner);
                    break;
                case 6:
                    return; // Return to the previous menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addAccount(Collection<BankAccount> accounts, Scanner scanner) {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        BankAccount account = new BankAccount(name, balance);
        accounts.add(account);

        System.out.println("Account added successfully.");
    }

    private static void viewAllAccounts(Collection<BankAccount> accounts) {
        System.out.println("All Accounts:");
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }

    private static void viewAccount(Collection<BankAccount> accounts, Scanner scanner) {
        System.out.print("Enter account holder's name to view: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (BankAccount account : accounts) {
            if (account.getAccountHolder().equalsIgnoreCase(name)) {
                System.out.println("Account Details:");
                System.out.println(account);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Account not found.");
        }
    }

    private static void updateAccount(Collection<BankAccount> accounts, Scanner scanner) {
        System.out.print("Enter account holder's name to update: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (BankAccount account : accounts) {
            if (account.getAccountHolder().equalsIgnoreCase(name)) {
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
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Account not found.");
        }
    }

    private static void deleteAccount(Collection<BankAccount> accounts, Scanner scanner) {
        System.out.print("Enter account holder's name to delete: ");
        String name = scanner.nextLine();
        Iterator<BankAccount> iterator = accounts.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            BankAccount account = iterator.next();
            if (account.getAccountHolder().equalsIgnoreCase(name)) {
                iterator.remove();
                System.out.println("Account deleted successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Account not found.");
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
