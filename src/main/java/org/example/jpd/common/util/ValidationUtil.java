package org.example.jpd.common.util;

import org.example.jpd.common.exception.ValidationException;

import java.util.function.Supplier;

/**
 * 校验工具类，用于验证输入的参数是否符合要求，若不符则抛出 {@link ValidationException} 异常。
 * <p>提供了文本、数值等简单的检验方法，简化了同时验证多个参数的情况。
 * <p>若多个参数同时检验，则无法确定具体不符的参数，只能确定是否有参数不符。
 */
public class ValidationUtil {

    @SafeVarargs
    public static void maxLength(int length, Supplier<String>... texts) throws ValidationException {
        for (Supplier<String> text : texts) {
            String str = text.get();
            //默认行为：如果为空值则忽略
            if (str == null) continue;

            if (str.length() > length) {
                throw new ValidationException("文本长度不能超过" + length + "个字符");
            }
        }
    }

    @SafeVarargs
    public static void range(double min, double max, Supplier<Double>... values) throws ValidationException {
        for (Supplier<Double> value : values) {
            double v = value.get();

            if (v < min || v > max) {
                throw new ValidationException("值必须在" + min + "到" + max + "之间");
            }
        }
    }

    @SafeVarargs
    public static void range(int min, int max, Supplier<Integer>... values) throws ValidationException {
        for (Supplier<Integer> value : values) {
            int v = value.get();

            if (v < min || v > max) {
                throw new ValidationException("值必须在" + min + "到" + max + "之间");
            }
        }
    }

    public static void notNull(Supplier<?>... values) throws ValidationException {
        for (Supplier<?> value : values) {
            if (value.get() == null) {
                throw new ValidationException("值不能为空");
            }
        }
    }
}
