package pers.qingxuan.wrsv2.conf;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午9:32 2020/12/1
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void set(String source) {
        contextHolder.set(source);
    }

    public static void clear() {
        contextHolder.remove();
    }
}