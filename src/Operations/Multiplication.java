package Operations;

import Interface.Operation;

// Реалізація інтерфейсу для множення
public class Multiplication implements Operation {
    private final double operand1;
    private final double operand2;

    public Multiplication(double operand1, double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public double execute() {
        return operand1 * operand2;
    }
}