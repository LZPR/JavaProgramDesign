package org.example.jpd.service;

import org.example.jpd.common.util.AlgorithmUtil;
import org.example.jpd.entity.FibonacciEntity;

public class FibonacciService {

    public FibonacciEntity getFibonacci(FibonacciEntity fibonacciEntity) throws IllegalArgumentException, ArithmeticException {
        fibonacciEntity.setResult(getFibonacciString(fibonacciEntity.getInput()));
        return fibonacciEntity;
    }

    private String getFibonacciString(int n) throws IllegalArgumentException, ArithmeticException {
        long[] numbers = AlgorithmUtil.getFibonacci(n);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numbers.length; i++) {
            long number = numbers[i];
            sb.append(number);

            if (i != numbers.length - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
