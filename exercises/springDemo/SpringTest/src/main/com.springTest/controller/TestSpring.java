package controller;

import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

public class TestSpring {
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("service");
        User user = User.builder()
                .id(1)
                .username("admin")
                .password("<PASSWORD>")
                .email("<EMAIL>")
                .build();
        userService.save(user);
    }
}
