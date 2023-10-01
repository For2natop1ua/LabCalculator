package Operations;

import Interface.Operation;

// Реалізація інтерфейсу для ділення
class Division implements Operation {
    private final double operand1;
    private final double operand2;

    public Division(double operand1, double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public double execute() {
        if (operand2 == 0) {
            throw new ArithmeticException("Ділення на нуль неможливе");
        }
        return operand1 / operand2;
    }
}