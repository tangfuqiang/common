package main.com.tfq.common.util;

import java.lang.reflect.Field;

public class ClassUtil {

    /**
     * 获取对象属性值
     *
     * @param t    对象
     * @param name 属性名
     * @return
     */
    public static <T> Object getData(T t, String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = t.getClass().getDeclaredField(name);
        boolean check = field.isAccessible();
        Object object = null;
        if (!check) {
            field.setAccessible(true);
            object = field.get(t);
            field.setAccessible(check);
            return object;
        }
        object = field.get(t);
        return object;
    }
}
