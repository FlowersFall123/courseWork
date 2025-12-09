package constructor;

import entity.GirlFriend;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class BoyPNameSpace {
    @Getter
    @Setter
    private GirlFriend girlFriend;

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BoyPNameSpace boy = (BoyPNameSpace) applicationContext.getBean("boyPNameSpace");
        log.info("{}", boy.girlFriend.speak());
    }
}
