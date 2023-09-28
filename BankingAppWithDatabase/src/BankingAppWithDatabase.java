import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class BankingAppWithDatabase {
    private static Connection connection;

    public static void main(String[] args) {
        try {
            connectToDatabase();
            createTableIfNotExists();

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
                        disconnectFromDatabase();
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void connectToDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/banking_app";
        String username = "your_username";
        String password = "your_password";

        connection = DriverManager.getConnection(url, username, password);
    }

    private static void createTableIfNotExists() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS accounts (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "account_holder VARCHAR(255)," +
                "balance DOUBLE)";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        }
    }

    private static void addAccount(Scanner scanner) throws SQLException {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Account holder's name cannot be empty.");
            return;
        }

        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        String insertSQL = "INSERT INTO accounts (account_holder, balance) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, balance);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int accountId = generatedKeys.getInt(1);
                    System.out.println("Account added successfully. Account ID: " + accountId);
                }
            } else {
                System.out.println("Account addition failed.");
            }
        }
    }

    // Implement other methods for CRUD operations and statistics as needed

    private static void disconnectFromDatabase() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
