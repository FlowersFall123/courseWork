package constructor;

import entity.GirlFriend;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class BoySetter {
    @Getter
    @Setter
    private GirlFriend girlFriend;

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BoySetter boySetter = (BoySetter) applicationContext.getBean("boySetter");
        log.info("{}", boySetter.getGirlFriend().speak());
    }
}
