public class simpleCalculatorCLI {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Calculator <operation> <operand1> <operand2>");
            System.exit(1);
        }

        String operation = args[0];
        double operand1 = Double.parseDouble(args[1]);
        double operand2 = Double.parseDouble(args[2]);

        double result = 0;

        switch (operation) {
            case "add":
                result = operand1 + operand2;
                break;
            case "subtract":
                result = operand1 - operand2;
                break;
            case "multiply":
                result = operand1 * operand2;
                break;
            case "divide":
                if (operand2 == 0) {
                    System.out.println("Error: Division by zero");
                    System.exit(1);
                }
                result = operand1 / operand2;
                break;
            default:
                System.out.println("Invalid operation. Please choose from 'add', 'subtract', 'multiply', or 'divide'.");
                System.exit(1);
        }

        System.out.println("Result: " + result);
    }
}
