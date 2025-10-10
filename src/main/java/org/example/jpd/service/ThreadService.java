package org.example.jpd.service;

import org.example.jpd.common.util.ThreadUtil;

import java.util.List;

public class ThreadService {
    public void startAllThreads() {
        List<Thread> threads = ThreadUtil.THREADS;
        for (Thread thread : threads) {
            ThreadUtil.startThread(thread);
        }
    }

    public void clearResult() {
        ThreadUtil.clearResult();
    }
}
