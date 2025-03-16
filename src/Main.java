import java.util.*;
import java.util.regex.*;

public class Main {
    private static List<String> history = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Calculator!");

        while (true) {
            System.out.print("\nPlease enter your arithmetic expression: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("history")) {
                showHistory();
                continue;
            }

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using the Calculator!");
                break;
            }

            try {
                double result = evaluateExpression(input);
                System.out.println("Result: " + result);
                history.add(input + " = " + result);
            } catch (Exception e) {
                System.out.println("Error: Invalid input or mathematical error.");
            }

            System.out.print("Do you want to continue? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("n")) {
                System.out.println("Thank you for using the Calculator!");
                break;
            }
        }

        scanner.close();
    }

    private static double evaluateExpression(String expression) {
        expression = preprocessFunctions(expression);
        return evaluatePostfix(infixToPostfix(expression));
    }

    private static String preprocessFunctions(String expression) {
        expression = expression.replaceAll("\\s+", "");

        Pattern pattern = Pattern.compile("(sqrt|abs|round)\\((-?[\\d.]+)\\)");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String func = matcher.group(1);
            double value = Double.parseDouble(matcher.group(2));

            double result = switch (func) {
                case "sqrt" -> Math.sqrt(value);
                case "abs" -> Math.abs(value);
                case "round" -> Math.round(value);
                default -> throw new IllegalArgumentException("Invalid function: " + func);
            };

            expression = expression.replace(matcher.group(0), String.valueOf(result));
        }

        return expression;
    }

    private static List<String> infixToPostfix(String expression) {
        Map<Character, Integer> precedence = Map.of(
                '+', 1, '-', 1, '*', 2, '/', 2, '%', 2, '^', 3
        );

        Stack<Character> operators = new Stack<>();
        List<String> output = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch) || ch == '.') {
                number.append(ch);
            } else {
                if (number.length() > 0) {
                    output.add(number.toString());
                    number.setLength(0);
                }

                if (ch == '(') {
                    operators.push(ch);
                } else if (ch == ')') {
                    while (!operators.isEmpty() && operators.peek() != '(') {
                        output.add(String.valueOf(operators.pop()));
                    }
                    operators.pop();
                } else {
                    while (!operators.isEmpty() && precedence.getOrDefault(operators.peek(), 0) >= precedence.getOrDefault(ch, 0)) {
                        output.add(String.valueOf(operators.pop()));
                    }
                    operators.push(ch);
                }
            }
        }

        if (number.length() > 0) {
            output.add(number.toString());
        }

        while (!operators.isEmpty()) {
            output.add(String.valueOf(operators.pop()));
        }

        return output;
    }

    private static double evaluatePostfix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();

        for (String token : postfix) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();

                switch (token) {
                    case "+" -> stack.push(a + b);
                    case "-" -> stack.push(a - b);
                    case "*" -> stack.push(a * b);
                    case "/" -> {
                        if (b == 0) throw new ArithmeticException("Division by zero");
                        stack.push(a / b);
                    }
                    case "%" -> stack.push(a % b);
                    case "^" -> stack.push(Math.pow(a, b));
                }
            }
        }

        return stack.pop();
    }

    private static void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No history available.");
        } else {
            System.out.println("Calculation History:");
            for (String record : history) {
                System.out.println(record);
            }
        }
    }
}
