package org.example.jpd.common.util;

import org.example.jpd.entity.BoxEntity;

/**
 * 算法工具类。由于省略了 Service 层，所以统一封装成静态方法，方便直接调用。
 */
public class AlgorithmUtil {

    public static String getFibonacciString(int n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException("n必须为非负整数");
        }

        if (n == 0) {
            return "";
        }

        if (n == 1) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        long first = 0;
        long second = 1;

        result.append(first);
        result.append(" ").append(second);

        for (int i = 3; i <= n; i++) {
            long next = first + second;
            result.append(" ").append(next);
            first = second;
            second = next;
        }

        return result.toString();
    }

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

    public static void calculateBox(BoxEntity box) {
        box.setVolume(box.getLength() * box.getHeight() * box.getWidth());
        box.setWeight(box.getVolume() * box.getDensity());
    }

    public static int getMaxNumberInArray(int[] arr) {
        int l = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[l]) {
                l = i;
            }
        }
        return arr[l];
    }
}
