package org.example.jpd.common.thread;

import org.example.jpd.common.util.AlgorithmUtil;
import org.example.jpd.common.util.ThreadUtil;

public class LeapThread extends Thread {

    @Override
    public void run() {
        for(int i = 2000; i <= 2100; i++) {
            if(AlgorithmUtil.isLeapYear(i)) {
                ThreadUtil.appendResult(i + "是闰年");
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                ThreadUtil.handleException(e);
            }
        }
    }
}
