package org.example.jpd.common.util;

import org.example.jpd.common.runnable.LeapThreadRunnable;
import org.example.jpd.common.runnable.Thread2Runnable;
import org.example.jpd.common.thread.LeapThread;
import org.example.jpd.common.thread.PrimeThread;

import java.util.List;

/**
 * 线程工具类，采用单例模式管理，防止重复创建线程。
 * <p>{@link #RESULT}会统一保存线程输出的信息，方便读取。
 */
public class ThreadUtil {
    public static final StringBuffer RESULT = new StringBuffer();

    public static final Thread LEAP_THREAD = new LeapThread();
    public static final Thread PRIME_THREAD = new PrimeThread();

    public static final Thread LEAP_THREAD_RUNNABLE = new Thread(new LeapThreadRunnable());
    public static final Thread THREAD2_RUNNABLE = new Thread(new Thread2Runnable());

    public static final List<Thread> THREADS = List.of(LEAP_THREAD, PRIME_THREAD, THREAD2_RUNNABLE);

    public static boolean startThread(Thread thread) {
        try {
            thread.start();
            return true;
        }
        catch (IllegalThreadStateException e){
            return false;
        }
    }

    public static void handleException(Exception ex) {
        RESULT.append(ex.getMessage() + "<br>");
    }

    public static void appendResult(String result) {
        //不能多次 append，会出现不同步的问题
        RESULT.append(result + "<br>");
    }

    public static void clearResult() {
        RESULT.setLength(0);
    }
}
