package auto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component("boyPNameSpace")
public class BoyPNameSpace {
    @Getter
    @Setter
    UsaGirlFriend usaGirlFriend;

    @Autowired
    public void setUsaGirlFriend(UsaGirlFriend usaGirlFriend) {
        this.usaGirlFriend=usaGirlFriend;
    }

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BoyPNameSpace boy = applicationContext.getBean("boyPNameSpace", BoyPNameSpace.class);
        log.info("{}", boy.usaGirlFriend.speak());
    }
}
