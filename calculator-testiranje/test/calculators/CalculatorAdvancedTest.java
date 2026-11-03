package calculators;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;
import exceptions.NotSupportedOperationException;
import exceptions.NumberNotInAreaException;

/**
 * Unit tests for CalculatorAdvanced.
 * Covers factorial, exponentiation, Armstrong numbers, and Perfect numbers.
 *
 * @author Andrea Glisic
 * @version 1.1
 * @since 2026-03-13
 */
public class CalculatorAdvancedTest {
    private CalculatorAdvanced advCalc;

    /**
     * Initializes a new CalculatorAdvanced instance before each test.
     */
    @BeforeEach
    void setUp() { advCalc = new CalculatorAdvanced(); }

    /**
     * Provides test data for the exponentiation test.
     *
     * @return a stream of arguments containing base value, exponent character,
     *         and expected result.
     */
    private static Stream<Arguments> powerValues() {
        return Stream.of(
            Arguments.of(2.0, '3', 8.0),
            Arguments.of(5.0, '0', 1.0),
            Arguments.of(0.0, '5', 0.0),
            Arguments.of(-2.0, '2', 4.0),
            Arguments.of(-2.0, '3', -8.0),
            Arguments.of(2.0, '9', 512.0)
        );
    }

    /**
     * Tests the exponentiation functionality.
     *
     * @param start  the base value.
     * @param action the exponent as a character ('0'-'9').
     * @param expected the expected result.
     * @throws Exception if an unexpected error occurs.
     */
    @ParameterizedTest
    @MethodSource("powerValues")
    void testPower(Double start, char action, Double expected) throws Exception {
        advCalc.setCurrentValue(start);
        advCalc.calculateAdvanced(action);
        assertThat(advCalc.getCurrentValue(), is(equalTo(expected)));
    }

    /**
     * Provides test data for the valid factorial test.
     *
     * @return a stream of arguments containing input value and expected factorial result.
     */
    private static Stream<Arguments> factorialValidValues() {
        return Stream.of(
            Arguments.of(0.0, 1.0),
            Arguments.of(1.0, 1.0),
            Arguments.of(5.0, 120.0),
            Arguments.of(10.0, 3628800.0)
        );
    }

    /**
     * Tests factorial with valid input values.
     *
     * @param start    the input value.
     * @param expected the expected factorial result.
     * @throws Exception if an unexpected error occurs.
     */
    @ParameterizedTest
    @MethodSource("factorialValidValues")
    void testFactorialValid(Double start, Double expected) throws Exception {
        advCalc.setCurrentValue(start);
        advCalc.calculateAdvanced('!');
        assertThat(advCalc.getCurrentValue(), is(equalTo(expected)));
    }

    /**
     * Provides test data for the factorial out-of-range test.
     *
     * @return a stream of arguments containing invalid input value, factorial
     *         operator, and expected exception message.
     */
    private static Stream<Arguments> factorialAreaValues() {
        return Stream.of(
            Arguments.of(-1.0, '!', "Vrijednost za faktorijel mora biti u opsegu [0,10]."),
            Arguments.of(11.0, '!', "Vrijednost za faktorijel mora biti u opsegu [0,10].")
        );
    }

    /**
     * Tests if NumberNotInAreaException is thrown for out-of-range factorial inputs.
     *
     * @param start  the invalid input value.
     * @param action the factorial operator '!'.
     * @param msg    the expected exception message.
     */
    @ParameterizedTest
    @MethodSource("factorialAreaValues")
    void testFactorialAreaException(Double start, char action, String msg) {
        advCalc.setCurrentValue(start);
        Exception ex = assertThrows(NumberNotInAreaException.class, () -> advCalc.calculateAdvanced(action));
        assertThat(ex.getMessage(), is(equalTo(msg)));
    }

    /**
     * Provides test data for the unsupported advanced operation test.
     *
     * @return a stream of arguments containing initial value and invalid action character.
     */
    private static Stream<Arguments> unsupportedAdvancedValues() {
        return Stream.of(
            Arguments.of(5.0, 'A'),
            Arguments.of(5.0, 'z')
        );
    }

    /**
     * Tests if NotSupportedOperationException is thrown for unsupported action characters.
     *
     * @param start  the initial calculator value.
     * @param action the invalid action character.
     */
    @ParameterizedTest
    @MethodSource("unsupportedAdvancedValues")
    void testAdvancedUnsupportedException(Double start, char action) {
        advCalc.setCurrentValue(start);
        Exception ex = assertThrows(NotSupportedOperationException.class, () -> advCalc.calculateAdvanced(action));
        assertThat(ex.getMessage(), containsString("podržana"));
    }
    /**
     * Provides test data for the valid characteristic test.
     *
     * @return a stream of arguments containing the number, characteristic type,
     *         and expected boolean result.
     */
    private static Stream<Arguments> characteristicValidValues() {
        return Stream.of(
            Arguments.of(153.0, 'A', true),
            Arguments.of(370.0, 'A', true),
            Arguments.of(154.0, 'A', false),
            Arguments.of(6.0, 'P', true),
            Arguments.of(28.0, 'P', true),
            Arguments.of(1.0, 'A', true),
            Arguments.of(10.0, 'P', false),
            Arguments.of(1634.0, 'A', true),
            Arguments.of(12.0, 'A', false),
            Arguments.of(496.0, 'P', true)
        );
    }

    /**
     * Tests valid checks for Armstrong and Perfect number characteristics.
     * @param start Number to check.
     * @param type Characteristic type ('A' or 'P').
     * @param expected Boolean result.
     * @throws Exception if an unexpected error occurs.
     */
    @ParameterizedTest
    @MethodSource("characteristicValidValues")
    void testHasCharacteristicValid(Double start, char type, Boolean expected) throws Exception {
        advCalc.setCurrentValue(start);
        assertThat(advCalc.hasCharacteristic(type), is(equalTo(expected)));
    }

    /**
     * Provides test data for the unsupported characteristic test.
     *
     * @return a stream of arguments containing initial value and unsupported
     *         characteristic character.
     */
    private static Stream<Arguments> characteristicUnsupportedValues() {
        return Stream.of(
            Arguments.of(10.0, 'Z'),
            Arguments.of(10.0, 'X')
        );
    }

    /**
     * Tests if hasCharacteristic throws exception for unsupported characteristic types.
     * @param start Initial value.
     * @param type Unsupported char.
     */
    @ParameterizedTest
    @MethodSource("characteristicUnsupportedValues")
    void testHasCharacteristicUnsupported(Double start, char type) {
        advCalc.setCurrentValue(start);
        NotSupportedOperationException ex = assertThrows(NotSupportedOperationException.class,
            () -> advCalc.hasCharacteristic(type));
        assertThat(ex.getMessage(), containsString("nije podržan"));
    }

    /**
     * Provides test data for the out-of-range characteristic test.
     *
     * @return a stream of arguments containing a value less than 1
     *         and a characteristic type character.
     */

    private static Stream<Arguments> characteristicAreaValues() {
        return Stream.of(
            Arguments.of(0.0, 'A'),
            Arguments.of(-5.0, 'P')
        );
    }

    /**
     * Tests if hasCharacteristic throws exception for values outside the allowed range.
     * @param start Value less than 1.
     * @param type Characteristic type.
     */
    @ParameterizedTest
    @MethodSource("characteristicAreaValues")
    void testHasCharacteristicAreaException(Double start, char type) {
        advCalc.setCurrentValue(start);
        NumberNotInAreaException ex = assertThrows(NumberNotInAreaException.class,
            () -> advCalc.hasCharacteristic(type));
        assertThat(ex.getMessage(), is(equalTo("Vrijednost mora biti veća ili jednaka 1.")));
    }

    /**
     * Tests the behavior of hasCharacteristic when both the value is out of range
     * and the characteristic character is not supported.
     * Verifies that NotSupportedOperationException is thrown with priority
     * over NumberNotInAreaException when an unsupported character is provided.
     */
    @Test
    void testHasCharacteristicInvalidCharAndOutOfRange() {
        advCalc.setCurrentValue(0.0);
        assertThrows(NotSupportedOperationException.class,
            () -> advCalc.hasCharacteristic('Z'));
    }
}