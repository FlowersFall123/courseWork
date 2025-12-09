package service.impl;

import entity.User;
import service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public void save(User user) {
        System.out.println("保存用户信息到数据库！");
    }
}
