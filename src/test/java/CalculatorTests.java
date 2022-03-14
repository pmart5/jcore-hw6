import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class CalculatorTests {

    Calculator sut;

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of(2, 4), Arguments.of(3, 9), Arguments.of(-4, 16));
    }

    @BeforeAll
    public static void startedAll() {
        System.out.println("Тесты запушены");
    }

    @BeforeEach
    public void started() {
        sut = Calculator.instance.get();
        System.out.println("Тест запушен");
    }

    @AfterEach
    public void finished() {
        System.out.println("Тест выполнен");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Тесты выполнены");
    }

    @Test
    public void testPlusEquals() {
        int x = 2, y = 3, expected = 5;
        int result = sut.plus.apply(x, y);
        assertEquals(expected, result);
    }

    @Disabled
    @Test
    public void testPlusNotEquals() {
        int x = 2, y = 3, unexpected = 4;
        int result = sut.plus.apply(x, y);
        assertNotEquals(unexpected, result);
    }

    @Test
    public void testMinusEquals() {
        int x = -2, y = -3, expected = 1;
        int result = sut.minus.apply(x, y);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 0, -2})
    public void testMultiplyEquals(int x) {
        int y = 0, expected = 0;
        int result = sut.multiply.apply(x, y);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testPowEquals(int x, int expected) {
        int result = sut.pow.apply(x);
        assertEquals(expected, result);
    }

    @Test
    public void testDivideEquals() {
        int x = 3, y = 2, expected = 1;
        int result = sut.minus.apply(x, y);
        assertEquals(expected, result);
    }

    @Test
    public void testDivideDoesNotThrow() {
        int x = 3, y = 0;
        assertDoesNotThrow(() -> sut.divide.apply(x, y));
        System.out.println(" ");
    }

    @Test
    public void testAbsEquals() {
        int x = -3, expected = 3;
        int result = sut.abs.apply(x);
        assertEquals(expected, result);
    }

    @Test
    public void testAbsNotEquals() {
        int x = -3, unexpected = -3;
        int result = sut.abs.apply(x);
        assertNotEquals(unexpected, result);
    }

    @Test
    public void testIsPositiveTrue() {
        int x = 2;
        boolean result = sut.isPositive.test(x);
        assertTrue(result);
    }

    @Test
    public void testIsPositiveFalse() {
        int x = -2;
        boolean result = sut.isPositive.test(x);
        assertFalse(result);
    }

    //JUnit + Hamcrest

    @Test
    public void testPlusEqualsH() {
        int x = 2, y = 3, expected = 5;
        int result = sut.plus.apply(x, y);
        assertThat(result, is(equalTo(expected)));
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testPowEqualsH(int x, int expected) {
        int result = sut.pow.apply(x);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testAbsEqualsH() {
        int x = -3;
        int result = sut.abs.apply(x);
        assertThat(result, allOf(greaterThan(0)));
    }

    @Test
    public void testAbsNotEqualsH() {
        int x = -3, unexpected = -3;
        int result = sut.abs.apply(x);
        assertThat(result, is(not(unexpected)));
    }

    @Test
    public void testIsPositiveBooleanH() {
        int x = 2;
        boolean result = sut.isPositive.test(x);
        assertThat(result, instanceOf(boolean.class));
    }

    @Test
    public void testIsPositiveFalseH() {
        int x = -2;
        boolean expected = false;
        boolean result = sut.isPositive.test(x);
        assertThat(result, is(expected));
    }
}