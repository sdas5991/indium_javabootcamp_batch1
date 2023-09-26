import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Simple Calculator");
        System.out.println("Available operations: +, -, *, /");
        
        boolean continueCalculating = true;
        
        while (continueCalculating) {
            System.out.print("Enter the first number: ");
            double num1 = scanner.nextDouble();
            
            System.out.print("Choose an operator (+, -, *, /): ");
            char operator = scanner.next().charAt(0);
            
            System.out.print("Enter the second number: ");
            double num2 = scanner.nextDouble();
            
            double result = calculate(num1, operator, num2);
            
            if (Double.isNaN(result)) {
                System.out.println("Error: Invalid operator or division by zero.");
            } else {
                System.out.println("Result: " + result);
            }
            
            System.out.print("Do you want to continue? (yes/no): ");
            String choice = scanner.next().toLowerCase();
            
            if (!choice.equals("yes")) {
                continueCalculating = false;
            }
        }
        
        System.out.println("Simple Calculator app closed.");
        scanner.close();
    }
    
    public static double calculate(double num1, char operator, double num2) {
        double result = 0.0;
        
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    return Double.NaN; // Indicate division by zero
                }
                break;
            default:
                return Double.NaN; // Indicate invalid operator
        }
        
        return result;
    }
}
