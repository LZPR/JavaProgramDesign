package org.example.jpd.common.thread;

import org.example.jpd.common.util.AlgorithmUtil;
import org.example.jpd.common.util.ThreadUtil;

public class PrimeThread extends Thread {

    @Override
    public void run() {
        for(int i = 100; i <= 200; i++) {
            try {
            if(AlgorithmUtil.isPrimeNumber(i)) {
                ThreadUtil.appendResult(i + "是质数");
            }
                Thread.sleep(400);
            } catch (Exception e) {
                ThreadUtil.handleException(e);
            }
        }
    }
}
