package calculators;

import exceptions.NotSupportedOperationException;
import exceptions.NumberNotInAreaException;

/**
 * The CalculatorAdvanced class extends the basic Calculator to provide advanced 
 * mathematical functions such as factorials, exponentiation, and number 
 * characteristic checks (Armstrong and Perfect numbers).
 * * @author Andrea Glisic
 * @version 1.1
 * @since 2026-03-13
 */
public class CalculatorAdvanced extends Calculator {

    /**
     * Performs advanced mathematical operations including factorial and exponentiation.
     * Factorial is supported for integers in the range [0, 10].
     * Exponentiation is supported for integer exponents in the range [0, 9].
     *
     * @param action The operation to perform ('!' for factorial, '0'-'9' for exponent).
     * @throws NotSupportedOperationException If the action character is not recognized.
     * @throws NumberNotInAreaException      If the base value is out of the required range for factorial.
     */
    public void calculateAdvanced(char action)
            throws NotSupportedOperationException, NumberNotInAreaException {

        int base = getCurrentValue().intValue();

        // Factorial logic
        if (action == '!') {
            if (base < 0 || base > 10) {
                throw new NumberNotInAreaException(
                        "Vrijednost za faktorijel mora biti u opsegu [0,10].");
            }
            int factorial = 1;
            for (int i = 1; i <= base; i++) {
                factorial *= i;
            }
            setCurrentValue((double) factorial);
        }
        // Exponentiation logic
        else if (action >= '0' && action <= '9') {
            int exponent = action - '0';
            double result = 1;
            for (int i = 0; i < exponent; i++) {
                result *= base;
            }
            setCurrentValue(result);
        }
        else {
            throw new NotSupportedOperationException(
                    "Akcija '" + action + "' nije podržana.");
        }
    }

    /**
     * Checks if the current value has a specific mathematical characteristic.
     * Supported characteristics: Armstrong number ('A') and Perfect number ('P').
     *
     * @param value The character representing the check ('A' for Armstrong, 'P' for Perfect).
     * @return true if the number possesses the characteristic, false otherwise.
     * @throws NumberNotInAreaException      If the current value is less than 1.
     * @throws NotSupportedOperationException If the characteristic character is not supported.
     */
    public Boolean hasCharacteristic(char value)
            throws NumberNotInAreaException, NotSupportedOperationException {

        int number = getCurrentValue().intValue();

        
        if (value != 'A' && value != 'P') {
            throw new NotSupportedOperationException(
                    "Karakter '" + value + "' nije podržan.");
        }

        // Tek onda provjeri opseg
        if (number < 1) {
            throw new NumberNotInAreaException(
                    "Vrijednost mora biti veća ili jednaka 1.");
        }

        if (value == 'A') return isArmstrong(number);
        return isPerfect(number);
    }

    /**
     * Internal helper method to determine if a number is an Armstrong number.
     * * @param number The number to check.
     * @return true if the number is an Armstrong number.
     */
    private boolean isArmstrong(int number) {
        int original = number;
        int sum = 0;
        int digits = 0;
        int temp = number;

        while (temp > 0) {
            digits++;
            temp /= 10;
        }

        temp = number;
        while (temp > 0) {
            int digit = temp % 10;
            int power = 1;
            for (int i = 0; i < digits; i++) {
                power *= digit;
            }
            sum += power;
            temp /= 10;
        }
        return sum == original;
    }

    /**
     * Internal helper method to determine if a number is a Perfect number.
     * * @param number The number to check.
     * @return true if the sum of proper divisors equals the number.
     */
    private boolean isPerfect(int number) {
        int sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) sum += i;
        }
        return sum == number;
    }
}