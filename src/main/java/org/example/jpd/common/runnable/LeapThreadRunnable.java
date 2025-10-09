package org.example.jpd.common.runnable;

import org.example.jpd.common.util.AlgorithmUtil;
import org.example.jpd.common.util.ThreadUtil;

public class LeapThreadRunnable implements Runnable {

    @Override
    public void run() {
        for(int i = 2000; i <= 3000; i++) {
            if(AlgorithmUtil.isLeapYear(i)) {
                ThreadUtil.appendResult(i + "是闰年");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                ThreadUtil.handleException(e);
            }
        }
    }
}
