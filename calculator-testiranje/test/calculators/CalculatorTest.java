package calculators;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;
import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;

/**
 * Unit tests for the basic Calculator class.
 * This class ensures that all basic arithmetic operations and error handling
 * work correctly.
 *
 * @author Andrea Glisic
 * @version 1.1
 * @since 2026-03-13
 */
public class CalculatorTest {
    private Calculator calc;

    /**
     * Sets up a fresh Calculator instance before each test execution.
     */
    @BeforeEach
    void setUp() { calc = new Calculator(); }

    /**
     * Tests if the constructor correctly initializes the value to 0.0.
     */
    @Test
    void testConstructorAndInitialValue() {
        assertThat(calc.getCurrentValue(), is(equalTo(0.0)));
    }
    /**
     * Provides test data for the setter test.
     *
     * @return a stream of arguments containing double values to set and verify.
     */
    private static Stream<Arguments> setterValues() {
        return Stream.of(
            Arguments.of(55.5),
            Arguments.of(10.0),
            Arguments.of(-5.0)
        );
    }
    /**
     * Tests the setter method with various double values.
     *
     * @param val the value to set and verify.
     */
    
    @ParameterizedTest
    @MethodSource("setterValues")
    void testSetters(Double val) {
        calc.setCurrentValue(val);
        assertThat(calc.getCurrentValue(), is(equalTo(val)));
    }

    /**
     * Provides test data for valid arithmetic operations.
     *
     * @return a stream of arguments containing initial value, operator,
     *         operand, and expected result.
     */
    private static Stream<Arguments> validOperations() {
        return Stream.of(
            Arguments.of(0.0, '+', 5.0, 5.0),
            Arguments.of(10.0, '-', 3.0, 7.0),
            Arguments.of(4.0, '*', 2.5, 10.0),
            Arguments.of(10.0, '/', 4.0, 2.5)
        );
    }

    /**
     * Tests valid arithmetic operations for addition, subtraction,
     * multiplication, and division.
     *
     * @param start    the initial calculator value.
     * @param op       the arithmetic operator.
     * @param val      the value to use in the operation.
     * @param expected the expected resulting value.
     * @throws Exception if an unexpected error occurs.
     */
    @ParameterizedTest
    @MethodSource("validOperations")
    void testCalculateValid(Double start, char op, Double val, Double expected) throws Exception {
        calc.setCurrentValue(start);
        calc.calculate(val, op);
        assertThat(calc.getCurrentValue(), is(closeTo(expected, 0.0001)));
    }

    /**
     * Provides test data for the division by zero test.
     *
     * @return a stream of arguments containing initial value, division operator,
     *         zero divisor, and expected exception message.
     */
    private static Stream<Arguments> divisionByZeroValues() {
        return Stream.of(
            Arguments.of(10.0, '/', 0.0, "Cannot divide by zero"),
            Arguments.of(5.0, '/', 0.0, "Cannot divide by zero")
        );
    }

    /**
     * Tests if DivisionByZeroException is thrown when dividing by zero.
     *
     * @param start the initial calculator value.
     * @param op    the division operator.
     * @param val   the zero divisor.
     * @param msg   the expected exception message.
     */
    @ParameterizedTest
    @MethodSource("divisionByZeroValues")
    void testDivisionByZero(Double start, char op, Double val, String msg) {
        calc.setCurrentValue(start);
        Exception ex = assertThrows(DivisionByZeroException.class, () -> calc.calculate(val, op));
        assertThat(ex.getMessage(), is(equalTo(msg)));
    }

    /**
     * Provides test data for the unsupported operations test.
     *
     * @return a stream of arguments containing operand, invalid operator,
     *         and expected exception message.
     */
    private static Stream<Arguments> unsupportedOperations() {
        return Stream.of(
            Arguments.of(5.0, '%', "Operation not supported"),
            Arguments.of(5.0, '?', "Operation not supported"),
            Arguments.of(5.0, 'x', "Operation not supported")
        );
    }

    /**
     * Tests if NotSupportedOperationException is thrown for illegal operators.
     *
     * @param val the value for calculation.
     * @param op  the invalid operator.
     * @param msg the expected exception message.
     */
    @ParameterizedTest
    @MethodSource("unsupportedOperations")
    void testUnsupportedOperations(Double val, char op, String msg) {
        Exception ex = assertThrows(NotSupportedOperationException.class, () -> calc.calculate(val, op));
        assertThat(ex.getMessage(), is(equalTo(msg)));
    }

    /**
     * Provides test data for the null value test.
     *
     * @return a stream of arguments containing arithmetic operators to test
     *         with a null operand.
     */
    private static Stream<Arguments> nullValueOperators() {
        return Stream.of(
            Arguments.of('+'),
            Arguments.of('-'),
            Arguments.of('*'),
            Arguments.of('/')
        );
    }

    /**
     * Tests if IllegalArgumentException is thrown when a null value is passed.
     *
     * @param op the operator to use.
     */
    @ParameterizedTest
    @MethodSource("nullValueOperators")
    void testCalculateNullValue(char op) {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> calc.calculate(null, op));
        assertThat(ex.getMessage(), is(equalTo("Value cannot be null")));
    }
}