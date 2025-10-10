package org.example.jpd.service;

import org.example.jpd.common.util.AlgorithmUtil;
import org.example.jpd.entity.PrimeEntity;

public class PrimeService {

    public PrimeEntity testPrimeNumber(PrimeEntity primeEntity) {
        primeEntity.setResult(isPrimeNumber(primeEntity.getInput()) ? "是" : "否");
        return primeEntity;
    }

    private boolean isPrimeNumber(int x) throws IllegalArgumentException {
        return AlgorithmUtil.isPrimeNumber(x);
    }
}
