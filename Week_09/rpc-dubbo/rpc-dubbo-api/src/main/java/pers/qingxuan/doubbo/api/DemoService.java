package pers.qingxuan.doubbo.api;

import java.util.concurrent.CompletableFuture;

/**
 * <p> demo service
 *
 * @author : QingXuan
 * @since Created in 下午5:09 2020/12/14
 */
public interface DemoService {
    String sayHello(String name); // 同步调用
    // 异步调用
    default CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }
}