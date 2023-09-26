public class Calculator {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Calculator <operand1> <operand2> <operation>");
            System.exit(1);
        }

        double operand1 = Double.parseDouble(args[0]);
        double operand2 = Double.parseDouble(args[1]);
        String operation = args[2].toLowerCase();

        double result = 0;

        switch (operation) {
            case "add":
                result = add(operand1, operand2);
                break;
            case "sub":
                result = subtract(operand1, operand2);
                break;
            case "mul":
                result = multiply(operand1, operand2);
                break;
            case "div":
                if (operand2 == 0) {
                    System.out.println("Error: Division by zero");
                    System.exit(1);
                }
                result = divide(operand1, operand2);
                break;
            default:
                System.out.println("Invalid operation. Please choose from 'add', 'sub', 'mul', or 'div'.");
                System.exit(1);
        }

        System.out.println("Result: " + result);
    }

    public static double add(double x, double y) {
        return x + y;
    }

    public static double subtract(double x, double y) {
        return x - y;
    }

    public static double multiply(double x, double y) {
        return x * y;
    }

    public static double divide(double x, double y) {
        return x / y;
    }
}
