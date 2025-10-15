package org.example.jpd.common.util;

import jakarta.servlet.http.HttpServletRequest;
import org.example.jpd.common.exception.InvalidBeanException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bean 工具类，通过反射简化 Bean 的读写及转换。
 */
public class BeanUtil {

    //根据类型，将字符串转换成对应的对象
    private final static Map<Class<?>, Parser<?>> parsers = Map.of(
            Integer.class, Integer::parseInt,
            int.class, Integer::parseInt,
            Double.class, Double::parseDouble,
            double.class, Double::parseDouble
    );

    public static <T> T parseParams(Class<T> type, HttpServletRequest req) throws NumberFormatException, InvalidBeanException {
        T instance = newInstance(type);
        Field[] fields = type.getDeclaredFields();

        for (Field field : fields) {
            String param = req.getParameter(field.getName());

            //不存在或为空则直接跳过，防止影响其它参数的读取
            if (param == null || param.isEmpty()) {
                continue;
            }

            try {
                Class<?> fieldType = field.getType();
                Method setMethod = type.getMethod(wrapWithSetter(field.getName()), fieldType);

                if(fieldType == String.class) {
                    setMethod.invoke(instance, param);
                    continue;
                }

                Parser<?> parser = parsers.get(fieldType);

                if (parser == null) {
                    throw new ClassCastException("不支持的类型：" + fieldType);
                }

                setMethod.invoke(instance, parser.parse(param));
            } catch (NumberFormatException e) {
                throw e;
            } catch (NoSuchMethodException e) {
                throw new InvalidBeanException("字段不存在 setter 方法：" + field.getName());
            } catch (Exception e) {
                //如果发生其它异常，属性将会是默认值
                continue;
            }
        }

        return instance;
    }

    public static <T> List<T> parseResult(Class<T> type, ResultSet rs, String prefix) throws SQLException, InvalidBeanException {
        Field[] fields = type.getDeclaredFields();

        //缓存所有数据库中存在的 setter 方法，避免重复查找
        Map<String, Method> setMethods = new HashMap<>();

        for (Field field : fields) {
            String columnName = prefix + field.getName();

            //不存在则直接跳过，防止影响其它列的读取
            if (!SqlUtil.isColumnExists(rs, columnName)) {
                continue;
            }

            try {
                Method setMethod = type.getMethod(wrapWithSetter(field.getName()), field.getType());
                setMethods.put(columnName, setMethod);
            } catch (NoSuchMethodException e) {
                throw new InvalidBeanException("字段不存在 setter 方法：" + field.getName());
            } catch (Exception e) {
                //如果发生其它异常，则不会设置该属性，属性将会是默认值
                continue;
            }
        }

        List<T> result = new ArrayList<>();

        while (rs.next()) {
            T instance = newInstance(type);

            for (Map.Entry<String, Method> entry : setMethods.entrySet()) {
                String columnName = entry.getKey();
                Method setMethod = entry.getValue();

                try {
                    Object value = rs.getObject(columnName);
                    setMethod.invoke(instance, value);
                } catch (SQLException e) {
                    throw e;
                } catch (Exception e) {
                    //如果发生其它异常，属性将会是默认值
                    continue;
                }
            }

            result.add(instance);
        }

        return result;
    }

    private static <T> T newInstance(Class<T> type) throws InvalidBeanException {
        T instance;

        try {
            instance = type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new InvalidBeanException("无法创建 Bean", e);
        }

        return instance;
    }

    private static String wrapWithSetter(String fieldName) {
        // int input -> setInput(int)
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    @FunctionalInterface
    interface Parser<T> {
        T parse(String value);
    }
}
