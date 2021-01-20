package pers.qingxuan.fastmq.client.util;

import pers.qingxuan.fastmq.client.common.ConfigException;

/**
 * <p> 反射操作工具类
 *
 * @author : Ray.fuxudong
 * @since Created in 12:32 2021/1/20
 */
public class ReflectUtils {

    public static <V> V newInstance(String className, Class<V> t) throws Exception {
        Class<?> klass = parseType(className);
        Object obj = klass.getDeclaredConstructor().newInstance();
        return t.cast(obj);
    }

    public static Class<?> parseType(String className) {
        try {
            ClassLoader classLoader = ReflectUtils.class.getClassLoader();
            return classLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new ConfigException("Expected a Class instance or class name.");
        }
    }
}
