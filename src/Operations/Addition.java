package Operations;

import Interface.Operation;

// Реалізація інтерфейсу для додавання
public class Addition implements Operation {
    private final double operand1;
    private final double operand2;

    public Addition(double operand1, double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public double execute() {
        return operand1 + operand2;
    }
}