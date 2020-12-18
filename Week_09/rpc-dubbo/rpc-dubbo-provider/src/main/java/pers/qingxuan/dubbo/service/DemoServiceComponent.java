package pers.qingxuan.dubbo.service;

import org.apache.dubbo.config.annotation.DubboService;
import pers.qingxuan.doubbo.api.DemoService;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午5:11 2020/12/14
 */
@DubboService(version = "1.0.0")
public class DemoServiceComponent implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }
}
