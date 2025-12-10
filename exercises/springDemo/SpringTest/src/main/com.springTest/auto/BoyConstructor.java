package auto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class BoyConstructor {

    
    private GirlFriend girlFriend;
    private static ApplicationContext applicationContext;
    public BoyConstructor(){}

    public BoyConstructor(ChinaGirlFriend girlFriend){
        this.girlFriend = girlFriend;
    }

    public static void main(String[] args) {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BoyConstructor boyConstructor = (BoyConstructor) applicationContext.getBean("boyConstructor");
        log.info("{}", boyConstructor.girlFriend.speak());
    }
}
