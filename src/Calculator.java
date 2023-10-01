import Parser.ExpressionCalculator;
import Parser.ExpressionParser;

import java.util.List;
import java.util.Scanner;

// Клас калькулятора
public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть вираз (наприклад, 2+2*2): ");
        String expression = scanner.nextLine();

        List<String> infixTokens = ExpressionParser.tokenize(expression);
        List<String> postfixTokens = ExpressionParser.infixToPostfix(infixTokens);
        double result = ExpressionCalculator.evaluatePostfix(postfixTokens);

        System.out.println("Результат: " + result);
    }
}