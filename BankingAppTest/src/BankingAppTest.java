import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BankingAppTest {
    private BankingApp bankingApp;

    @BeforeEach
    public void setUp() {
        bankingApp = new BankingApp();
    }

    @Test
    public void testAddAccount() {
        // Simulate user input for adding an account
        String input = "John Doe\n1000\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        bankingApp.addAccount();

        // Perform assertions to verify that the account was added correctly
        assertEquals(1, bankingApp.getAllAccounts().size());
    }

    @Test
    public void testViewAllAccounts() {
        // Add some accounts to the app
        bankingApp.addAccount("Alice", 2000);
        bankingApp.addAccount("Bob", 1500);

        // Redirect the console output for testing
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to view all accounts
        bankingApp.viewAllAccounts();

        // Perform assertions to verify the output
        String expectedOutput = "All Accounts:\nAccount Holder: Alice, Balance: $2000.0\nAccount Holder: Bob, Balance: $1500.0\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testViewAccount() {
        // Add an account to the app
        bankingApp.addAccount("Carol", 3000);

        // Redirect the console output for testing
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to view the account
        bankingApp.viewAccount(1);

        // Perform assertions to verify the output
        String expectedOutput = "Account Details (ID: 1):\nAccount Holder: Carol, Balance: $3000.0\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    // Similar tests for other methods like updateAccount, deleteAccount, and statistics

    @AfterEach
    public void tearDown() {
        // Reset the input and output streams
        System.setIn(System.in);
        System.setOut(System.out);
    }
}
