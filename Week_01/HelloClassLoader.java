import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p> HelloClassLoader
 *
 * @author : qingxun
 * @since Created in 2:14 下午 2020/10/17
 */
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = new HelloClassLoader().findClass("Hello");
        Method method = clazz.getMethod("hello");
        method.invoke(clazz.getDeclaredConstructor().newInstance());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try (InputStream input = new FileInputStream(new File("./Hello.xlass"))) {
            byte[] bytes = input.readAllBytes();
            int length = bytes.length;
            for (int i = 0; i < length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException(e.getMessage(), e);
        }
    }
}