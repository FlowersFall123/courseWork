package auto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 注解方式实现依赖注入
 */
@Component("usaGirlFriend")
public class UsaGirlFriend implements GirlFriend{
    @Getter
    @Setter
    @Value("English")
    private String language;
    @Override
    public String speak() {
        return language;
    }
}
