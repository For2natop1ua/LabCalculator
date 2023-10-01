package Parser;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// Клас для обчислення виразів
class ExpressionCalculator {
    public static double evaluatePostfix(List<String> postfixTokens) {
        Deque<Double> operandStack = new ArrayDeque<>();

        for (String token : postfixTokens) {
            if (ExpressionParser.isNumber(token)) {
                operandStack.push(Double.parseDouble(token));
            } else if (ExpressionParser.isOperator(token.charAt(0))) {
                if (operandStack.size() < 2) {
                    throw new IllegalArgumentException("Недостатньо операндів для операції");
                }
                double operand2 = operandStack.pop();
                double operand1 = operandStack.pop();
                double result = performOperation(operand1, operand2, token.charAt(0));
                operandStack.push(result);
            }
        }

        if (operandStack.size() != 1) {
            throw new IllegalArgumentException("Недостатньо операторів у виразі");
        }

        return operandStack.pop();
    }

    private static double performOperation(double operand1, double operand2, char operator) {
        Operation operation = switch (operator) {
            case '+' -> new Addition(operand1, operand2);
            case '-' -> new Subtraction(operand1, operand2);
            case '*' -> new Multiplication(operand1, operand2);
            case '/' -> new Division(operand1, operand2);
            default -> throw new IllegalArgumentException("Невідомий оператор: " + operator);
        };
        return operation.execute();
    }
}