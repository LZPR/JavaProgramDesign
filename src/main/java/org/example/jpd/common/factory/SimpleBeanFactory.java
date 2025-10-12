package org.example.jpd.common.factory;

import org.example.jpd.common.exception.InvalidBeanException;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简易工厂，主要用于管理 {@code Service} 实例，为方便使用而采用懒加载的策略（第一次获取时才创建实例）
 */
public class SimpleBeanFactory {

    private static final Map<Class<?>, Object> instancesMap = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> type) throws InvalidBeanException {
        Objects.requireNonNull(type);

        T instance = (T) instancesMap.get(type);

        if (instance == null) {
            try {
                instance = type.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new InvalidBeanException("无法创建 Bean", e);
            }

            instancesMap.put(type, instance);
        }

        return instance;
    }
}
