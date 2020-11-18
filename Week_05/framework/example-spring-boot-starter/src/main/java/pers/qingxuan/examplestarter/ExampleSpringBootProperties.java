package pers.qingxuan.examplestarter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p> 配置信息
 *
 * @author : QingXuan
 * @since Created in 下午8:10 2020/11/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ConfigurationProperties(prefix = "spring.example")
public class ExampleSpringBootProperties {
    private Integer studentId;
    private String studentName;
}
