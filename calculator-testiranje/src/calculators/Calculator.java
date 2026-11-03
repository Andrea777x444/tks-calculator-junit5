package calculators;

import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;

/**
 * The Calculator class provides basic arithmetic operations such as addition, 
 * subtraction, multiplication, and division. It maintains a current value 
 * that can be modified through calculations.
 * * @author Andrea Glisic
 * @version 1.1
 * @since 2026-03-13
 */
public class Calculator {
    private Double currentValue;

    /**
     * Initializes a new Calculator instance with the current value set to 0.0.
     */
    public Calculator() {
        this.currentValue = 0.0;
    }

    /**
     * Retrieves the current value of the calculator.
     * * @return The current calculated value as a Double.
     */
    public Double getCurrentValue() {
        return this.currentValue;
    }

    /**
     * Sets the current value of the calculator to a specified value.
     * * @param currentValue The new value to be set.
     */
    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * Performs a basic arithmetic operation on the current value.
     * * @param value    The value to be used in the operation.
     * @param operator The arithmetic operator ('+', '-', '*', '/').
     * @throws IllegalArgumentException       If the provided value is null.
     * @throws DivisionByZeroException        If division by zero is attempted.
     * @throws NotSupportedOperationException If the provided operator is not supported.
     */
    public void calculate(Double value, char operator) throws DivisionByZeroException, NotSupportedOperationException {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        
        if (operator == '+') {
            currentValue += value;
        } else if (operator == '-') {
            currentValue -= value;
        } else if (operator == '*') {
            currentValue *= value;
        } else if (operator == '/') {
            if (value == 0) {
                throw new DivisionByZeroException("Cannot divide by zero");
            }
            currentValue /= value;
        } else {
            throw new NotSupportedOperationException("Operation not supported");
        }
    }
}