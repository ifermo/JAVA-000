package pers.qingxuan.beans.factory;

import org.springframework.beans.factory.FactoryBean;

/**
 * <p> 借助FactoryBean创建Bean
 *
 * @author : QingXuan
 * @since Created in 下午10:49 2020/11/16
 */
public class ColorFactoryBean implements FactoryBean<Color> {

    @Override
    public Color getObject() throws Exception {
        return new Color(0xFF0000);
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }
}
