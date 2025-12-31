package com.example.backend.controller.user;

import com.example.backend.entity.RestBean;
import com.example.backend.entity.dto.SendMessage;
import com.example.backend.entity.po.User;
import com.example.backend.entity.vo.MessageVO;
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

    @PostMapping("/InsertMessage")
    public RestBean<String> InsertMessage(@RequestBody SendMessage sendMessage)
    {
        Integer status=userService.InsertMessage(sendMessage);
        if(status==1) return RestBean.success("发送成功");
        else return RestBean.failure(500,"发送失败");
    }

    @GetMapping("/getMessageByToUserId")
    public RestBean<List<MessageVO>> getMessageByToUserId(){
        return RestBean.success("cg",userService.getMessageByToUserId());
    }

    @GetMapping("/getAllUser")
    public RestBean<List<User>> getAllUser()
    {
        return RestBean.success("cg",userService.getAllUser());
    }
}
