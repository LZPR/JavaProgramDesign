package org.example.jpd.common.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 日志工具类。将 JDK 原本的 {@link Logger} 进行了简单的封装。
 */
public class LogUtil {

    private static final Logger logger = Logger.getLogger(LogUtil.class.getName());

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logException(Throwable throwable) {
        logger.log(Level.SEVERE, "发生异常", throwable);
    }
}
