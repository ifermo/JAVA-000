package pers.qingxuan.wrsv2.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import pers.qingxuan.wrsv2.conf.DataSourceContextHolder;

import java.lang.reflect.Method;

import static pers.qingxuan.wrsv2.consts.Constant.PRIMARY_SOURCE;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午9:36 2020/12/1
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Before("@annotation(pers.qingxuan.wrsv2.aop.DataSourceWith)")
    public void before(JoinPoint point) {
        MethodSignature sign = (MethodSignature) point.getSignature();
        Method method = sign.getMethod();
        DataSourceWith dataSourceWith = method.getAnnotation(DataSourceWith.class);
        String datasource = dataSourceWith == null ? PRIMARY_SOURCE : dataSourceWith.value();
        DataSourceContextHolder.set(datasource);
    }

    @After("@annotation(pers.qingxuan.wrsv2.aop.DataSourceWith)")
    public void after(JoinPoint point) {
        DataSourceContextHolder.clear();
    }
}
