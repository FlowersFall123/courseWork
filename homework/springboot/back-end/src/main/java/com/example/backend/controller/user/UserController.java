package com.example.backend.controller.user;

import com.example.backend.entity.RestBean;
import com.example.backend.entity.po.User;
import com.example.backend.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @Auther:fz
 * @Date:2025/5/30
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    UserService userService;

    @GetMapping("/getUserById")
    public RestBean<User>getUserById(HttpServletRequest request)
    {
        User user=userService.getUserById((Long) request.getAttribute("id"));
        user.setPassword("*");
        log.info("用户ID为{}",user.getId());
        return RestBean.success("cg",user);
    }


    @GetMapping("/getAllUser")
    public RestBean<List<User>> getAllUser()
    {
        return RestBean.success("cg",userService.getAllUser());
    }

    @PostMapping("/deleteUser/{id}")
    public RestBean<String> deleteUser(@PathVariable Long id) {
        Integer status=userService.deleteUser(id);
        if (status==0) return RestBean.failure(500,"删除失败");
        else return RestBean.success("cg");
    }

    @PostMapping("/updateUser")
    public RestBean<String> updateUser(@RequestBody User user) {
        Integer status=userService.updateUser(user);
        if (status==0) return RestBean.failure(500,"更新失败");
        else return RestBean.success("cg");
    }
}
