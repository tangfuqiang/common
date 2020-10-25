package com.tfq.common.util;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ListUtil {

    /**
     * @param list  待排序集合
     * @param sort  true升序 false降序
     * @param names 排序属性名 权重按顺序排列
     */
    public static <T> void sortList(List<T> list, boolean sort, String... names) {

        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                int ret = 0;
                for (int i = 0; i < names.length; i++) {
                    try {
                        Object object1 = ClassUtil.getData(o1, names[i]);
                        Object object2 = ClassUtil.getData(o2, names[i]);
                        ret = compareTo(object1, object2, sort);
                        if (ret != 0) {
                            break;
                        }
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                return ret;
            }
        });
    }

    private static  int compareTo(Object o1, Object o2, boolean sort) {
        String str1 = o1.toString();
        String str2 = o2.toString();
        if (o1 instanceof Number) {
            int maxLen = Math.max(str1.length(), str2.length());
            str1 = numToString((Number) o1, maxLen);
            str2 = numToString((Number) o2, maxLen);
        } else if (o1 instanceof Date) {
            long l1 = ((Date) o1).getTime();
            long l2 = ((Date) o1).getTime();
            int maxLen = String.valueOf(Math.max(l1, l2)).length();
            str1 = numToString(l1, maxLen);
            str2 = numToString(l2, maxLen);
        }
        int ret = 0;
        if (sort) {
            ret = str1.compareTo(str2);
        } else {
            ret = str2.compareTo(str1);
        }
        return ret;
    }

    /**
     * 数字补0
     *
     * @param number
     * @param len
     * @return
     */
    private static String numToString(Number number, int len) {
        NumberFormat nf = NumberFormat.getInstance();
        // 设置是否使用分组
        nf.setGroupingUsed(false);
        // 设置最大整数位数
        nf.setMaximumIntegerDigits(len);
        // 设置最小整数位数
        nf.setMinimumIntegerDigits(len);
        return nf.format(number);
    }
}
