package org.example.jpd.common.util;

/**
 * 算法工具类。抽取了公用的算法。
 */
public class AlgorithmUtil {

    public static boolean isPrimeNumber(int x) throws IllegalArgumentException {
        if (x <= 1) {
            throw new IllegalArgumentException("x必须为大于1的整数");
        }

        for (int j = 2; j <= Math.sqrt(x); j++) {
            if (x % j == 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    public static long[] getFibonacci(int n) throws IllegalArgumentException, ArithmeticException {
        if (n < 0) {
            throw new IllegalArgumentException("n必须为非负整数");
        }

        if (n == 0) {
            return new long[]{0};
        }

        if (n == 1) {
            return new long[]{1};
        }

        long[] result = new long[n];
        result[0] = 0;
        result[1] = 1;

        for (int i = 2; i < n; i++) {
            if(result[i - 2]> Long.MAX_VALUE - result[i - 1]) {
                throw new ArithmeticException("运算结果超过了所能表示的最大值");
            }

            long next = result[i - 2] + result[i - 1];
            result[i] = next;
        }

        return result;
    }
}
