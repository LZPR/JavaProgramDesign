package org.example.jpd.common.util;

public class StringUtil {

    public static int[] toArray(String input) throws NumberFormatException {
        // 全角转半角，正则可以过滤多余的空格
        String[] elements = input.replace('，', ',').split("(,|\\s)+");
        int[] arr = new int[elements.length];

        for (int i = 0; i < elements.length; i++) {
            arr[i] = Integer.parseInt(elements[i]);
        }

        return arr;
    }
}
