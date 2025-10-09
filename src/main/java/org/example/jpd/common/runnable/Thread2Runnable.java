package org.example.jpd.common.runnable;

import org.example.jpd.common.util.ThreadUtil;

public class Thread2Runnable implements Runnable {

    @Override
    public void run() {
        for(int i = 100; i <= 200; i++) {
            try {
                if(i % 3 == 0) {
                    ThreadUtil.appendResult(i + "能被3整除");
                }
                Thread.sleep(200);
            } catch (Exception e) {
                ThreadUtil.handleException(e);
            }
        }
    }
}
