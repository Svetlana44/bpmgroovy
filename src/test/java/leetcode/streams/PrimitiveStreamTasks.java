package leetcode.streams;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Задачи на примитивные стримы: IntStream, LongStream, DoubleStream.
 * Частые вопросы на собеседованиях по работе с примитивами.
 */
@Tag("leetcode")
class PrimitiveStreamTasks {

    // ========================= IntStream =========================

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Sum of Range                                                 │
    │ Верни сумму чисел от start до end (включительно).            │
    └──────────────────────────────────────────────────────────────┘
    */
    int sumRange(int start, int end) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Generate Squares                                             │
    │ Сгенерируй массив квадратов чисел от 1 до n.                 │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] generateSquares(int n) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Filter and Sum                                               │
    │ Отфильтруй чётные числа и верни их сумму.                    │
    └──────────────────────────────────────────────────────────────┘
    */
    int filterEvenAndSum(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Average of Array                                             │
    │ Верни среднее значение массива или 0.0 если массив пуст.     │
    └──────────────────────────────────────────────────────────────┘
    */
    double averageOfArray(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Find Min and Max                                             │
    │ Верни массив [min, max] или [-1, -1] если массив пуст.       │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] findMinMax(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Count Distinct                                               │
    │ Посчитай количество уникальных элементов.                    │
    └──────────────────────────────────────────────────────────────┘
    */
    long countDistinct(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Box to Integer Array                                         │
    │ Преобразуй int[] в Integer[] (boxing).                       │
    └──────────────────────────────────────────────────────────────┘
    */
    Integer[] boxArray(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Generate Fibonacci                                           │
    │ Сгенерируй первые n чисел Фибоначчи.                         │
    └──────────────────────────────────────────────────────────────┘
    */
    int[] generateFibonacci(int n) {
        throw new UnsupportedOperationException("TODO");
    }

    // ========================= LongStream =========================

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Factorial                                                    │
    │ Верни факториал числа n (n!).                                │
    └──────────────────────────────────────────────────────────────┘
    */
    long factorial(int n) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Sum of Squares Long                                          │
    │ Верни сумму квадратов от 1 до n (используй LongStream).      │
    └──────────────────────────────────────────────────────────────┘
    */
    long sumOfSquares(long n) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Count Primes in Range                                        │
    │ Посчитай количество простых чисел в диапазоне [2, n].        │
    └──────────────────────────────────────────────────────────────┘
    */
    long countPrimes(long n) {
        throw new UnsupportedOperationException("TODO");
    }

    // ========================= DoubleStream =========================

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Average of Doubles                                           │
    │ Верни среднее значение массива double.                       │
    └──────────────────────────────────────────────────────────────┘
    */
    double averageDoubles(double[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Sum of Positive Doubles                                      │
    │ Верни сумму положительных чисел.                             │
    └──────────────────────────────────────────────────────────────┘
    */
    double sumPositive(double[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Convert Int to Double                                        │
    │ Преобразуй int[] в double[] через IntStream.                 │
    └──────────────────────────────────────────────────────────────┘
    */
    double[] intToDouble(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /*
    ┌──────────────────────────────────────────────────────────────┐
    │ Standard Deviation                                           │
    │ Верни стандартное отклонение массива.                        │
    │ σ = sqrt(Σ(xi - μ)² / n), где μ - среднее значение.          │
    └──────────────────────────────────────────────────────────────┘
    */
    double standardDeviation(double[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // ========================= TESTS =========================

    @ParameterizedTest
    @MethodSource("sumRangeProvider")
    void sumRange_shouldSum(int start, int end, int expected) {
        assertEquals(expected, sumRange(start, end));
    }

    @ParameterizedTest
    @MethodSource("generateSquaresProvider")
    void generateSquares_shouldGenerate(int n, int[] expected) {
        assertArrayEquals(expected, generateSquares(n));
    }

    @ParameterizedTest
    @MethodSource("filterEvenSumProvider")
    void filterEvenAndSum_shouldFilterAndSum(int[] nums, int expected) {
        assertEquals(expected, filterEvenAndSum(nums));
    }

    @ParameterizedTest
    @MethodSource("averageProvider")
    void averageOfArray_shouldAverage(int[] nums, double expected) {
        assertEquals(expected, averageOfArray(nums), 0.001);
    }

    @ParameterizedTest
    @MethodSource("minMaxProvider")
    void findMinMax_shouldFind(int[] nums, int[] expected) {
        assertArrayEquals(expected, findMinMax(nums));
    }

    @ParameterizedTest
    @MethodSource("distinctProvider")
    void countDistinct_shouldCount(int[] nums, long expected) {
        assertEquals(expected, countDistinct(nums));
    }

    @ParameterizedTest
    @MethodSource("boxProvider")
    void boxArray_shouldBox(int[] input, Integer[] expected) {
        assertArrayEquals(expected, boxArray(input));
    }

    @ParameterizedTest
    @MethodSource("fibonacciProvider")
    void generateFibonacci_shouldGenerate(int n, int[] expected) {
        assertArrayEquals(expected, generateFibonacci(n));
    }

    @ParameterizedTest
    @MethodSource("factorialProvider")
    void factorial_shouldCalculate(int n, long expected) {
        assertEquals(expected, factorial(n));
    }

    @ParameterizedTest
    @MethodSource("sumSquaresProvider")
    void sumOfSquares_shouldSum(long n, long expected) {
        assertEquals(expected, sumOfSquares(n));
    }

    @ParameterizedTest
    @MethodSource("countPrimesProvider")
    void countPrimes_shouldCount(long n, long expected) {
        assertEquals(expected, countPrimes(n));
    }

    @ParameterizedTest
    @MethodSource("averageDoublesProvider")
    void averageDoubles_shouldAverage(double[] nums, double expected) {
        assertEquals(expected, averageDoubles(nums), 0.001);
    }

    @ParameterizedTest
    @MethodSource("sumPositiveProvider")
    void sumPositive_shouldSum(double[] nums, double expected) {
        assertEquals(expected, sumPositive(nums), 0.001);
    }

    @ParameterizedTest
    @MethodSource("intToDoubleProvider")
    void intToDouble_shouldConvert(int[] input, double[] expected) {
        assertArrayEquals(expected, intToDouble(input), 0.001);
    }

    @ParameterizedTest
    @MethodSource("stdDevProvider")
    void standardDeviation_shouldCalculate(double[] nums, double expected) {
        assertEquals(expected, standardDeviation(nums), 0.001);
    }

    // ========================= PROVIDERS =========================

    private static Stream<Arguments> sumRangeProvider() {
        return Stream.of(
            Arguments.of(1, 10, 55),
            Arguments.of(5, 5, 5),
            Arguments.of(1, 100, 5050)
        );
    }

    private static Stream<Arguments> generateSquaresProvider() {
        return Stream.of(
            Arguments.of(5, new int[]{1, 4, 9, 16, 25}),
            Arguments.of(3, new int[]{1, 4, 9}),
            Arguments.of(1, new int[]{1})
        );
    }

    private static Stream<Arguments> filterEvenSumProvider() {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 3, 4, 5, 6}, 12),
            Arguments.of(new int[]{1, 3, 5}, 0),
            Arguments.of(new int[]{2, 4, 6}, 12)
        );
    }

    private static Stream<Arguments> averageProvider() {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 3, 4, 5}, 3.0),
            Arguments.of(new int[]{10}, 10.0),
            Arguments.of(new int[]{}, 0.0)
        );
    }

    private static Stream<Arguments> minMaxProvider() {
        return Stream.of(
            Arguments.of(new int[]{3, 1, 4, 1, 5, 9}, new int[]{1, 9}),
            Arguments.of(new int[]{5}, new int[]{5, 5}),
            Arguments.of(new int[]{}, new int[]{-1, -1})
        );
    }

    private static Stream<Arguments> distinctProvider() {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 2, 3, 3, 3}, 3L),
            Arguments.of(new int[]{1, 1, 1}, 1L),
            Arguments.of(new int[]{1, 2, 3}, 3L)
        );
    }

    private static Stream<Arguments> boxProvider() {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 3}, new Integer[]{1, 2, 3}),
            Arguments.of(new int[]{}, new Integer[]{}),
            Arguments.of(new int[]{42}, new Integer[]{42})
        );
    }

    private static Stream<Arguments> fibonacciProvider() {
        return Stream.of(
            Arguments.of(5, new int[]{0, 1, 1, 2, 3}),
            Arguments.of(8, new int[]{0, 1, 1, 2, 3, 5, 8, 13}),
            Arguments.of(1, new int[]{0})
        );
    }

    private static Stream<Arguments> factorialProvider() {
        return Stream.of(
            Arguments.of(5, 120L),
            Arguments.of(0, 1L),
            Arguments.of(10, 3628800L)
        );
    }

    private static Stream<Arguments> sumSquaresProvider() {
        return Stream.of(
            Arguments.of(3L, 14L),
            Arguments.of(5L, 55L),
            Arguments.of(10L, 385L)
        );
    }

    private static Stream<Arguments> countPrimesProvider() {
        return Stream.of(
            Arguments.of(10L, 4L),
            Arguments.of(20L, 8L),
            Arguments.of(2L, 1L)
        );
    }

    private static Stream<Arguments> averageDoublesProvider() {
        return Stream.of(
            Arguments.of(new double[]{1.0, 2.0, 3.0}, 2.0),
            Arguments.of(new double[]{10.5}, 10.5),
            Arguments.of(new double[]{}, 0.0)
        );
    }

    private static Stream<Arguments> sumPositiveProvider() {
        return Stream.of(
            Arguments.of(new double[]{-1.0, 2.0, -3.0, 4.0}, 6.0),
            Arguments.of(new double[]{-1.0, -2.0}, 0.0),
            Arguments.of(new double[]{1.5, 2.5, 3.5}, 7.5)
        );
    }

    private static Stream<Arguments> intToDoubleProvider() {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 3}, new double[]{1.0, 2.0, 3.0}),
            Arguments.of(new int[]{}, new double[]{}),
            Arguments.of(new int[]{10}, new double[]{10.0})
        );
    }

    private static Stream<Arguments> stdDevProvider() {
        return Stream.of(
            Arguments.of(new double[]{2.0, 4.0, 4.0, 4.0, 5.0, 5.0, 7.0, 9.0}, 2.0),
            Arguments.of(new double[]{10.0, 10.0, 10.0}, 0.0),
            Arguments.of(new double[]{1.0, 2.0, 3.0, 4.0, 5.0}, 1.414)
        );
    }
}
