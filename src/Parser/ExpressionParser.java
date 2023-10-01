package Parser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// Клас для парсингу виразу
class ExpressionParser {
    public static List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for (char c : expression.toCharArray()) {
            if (c == ' ' || c == '\t') {
                continue; // Пропускаємо пробіли та табуляції
            }
            if (isOperator(c) || c == '(' || c == ')') {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                tokens.add(String.valueOf(c));
            } else {
                currentToken.append(c);
            }
        }

        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }

        return tokens;
    }

    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static List<String> infixToPostfix(List<String> infixTokens) {
        List<String> postfixTokens = new ArrayList<>();
        Deque<String> operatorStack = new ArrayDeque<>();

        for (String token : infixTokens) {
            if (isNumber(token)) {
                postfixTokens.add(token);
            } else if (isOperator(token.charAt(0))) {
                while (!operatorStack.isEmpty() && hasHigherPrecedence(operatorStack.peek(), token)) {
                    postfixTokens.add(operatorStack.pop());
                }
                operatorStack.push(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    postfixTokens.add(operatorStack.pop());
                }
                if (!operatorStack.isEmpty() && operatorStack.peek().equals("(")) {
                    operatorStack.pop();
                } else {
                    throw new IllegalArgumentException("Неспівпадіння дужок");
                }
            }
        }

        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek().equals("(")) {
                throw new IllegalArgumentException("Неспівпадіння дужок");
            }
            postfixTokens.add(operatorStack.pop());
        }

        return postfixTokens;
    }

    static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int getPrecedence(String operator) {
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else if (operator.equals("*") || operator.equals("/")) {
            return 2;
        }
        return 0;
    }

    private static boolean hasHigherPrecedence(String operator1, String operator2) {
        int precedence1 = getPrecedence(operator1);
        int precedence2 = getPrecedence(operator2);
        if (precedence1 == precedence2) {
            return !isRightAssociative(operator1);
        }
        return precedence1 > precedence2;
    }

    private static boolean isRightAssociative(String operator) {
        return operator.equals("^");
    }
}
