package org.example.jpd.common.util;

import jakarta.servlet.http.HttpServletRequest;
import org.example.jpd.common.exception.InvalidBeanException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Bean 工具类，通过反射简化 Bean 的读写及转换。
 */
public class BeanUtil {

    public static <T> T parseParams(Class<T> type, HttpServletRequest req) throws NumberFormatException, InvalidBeanException {
        Field[] fields = type.getDeclaredFields();
        T instance = null;

        try {
            instance = type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new InvalidBeanException("无法创建 Bean", e);
        }

        for (Field field : fields) {

            String param = req.getParameter(field.getName());

            if (param == null) {
                continue;
            }

            try {
                // int input -> setInput(int)
                Method setMethod = type.getMethod(wrapWithSetter(field.getName()), field.getType());

                if (field.getType().equals(String.class)) {
                    setMethod.invoke(instance, param);
                } else if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
                    setMethod.invoke(instance, Integer.parseInt(param));
                } else if (field.getType().equals(double.class) || field.getType().equals(Double.class)) {
                    setMethod.invoke(instance, Double.parseDouble(param));
                }
                //TODO: 添加更多的类型转换，或者抽象出一个类型转换接口做成模板模式
            } catch (NumberFormatException e) {
                throw e;
            } catch (NoSuchMethodException e) {
                throw new InvalidBeanException("字段不存在 setter 方法：" + field.getName());
            } catch (Exception e) {
                continue;
            }
        }

        return instance;
    }

    private static String wrapWithSetter(String fieldName) {
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}
