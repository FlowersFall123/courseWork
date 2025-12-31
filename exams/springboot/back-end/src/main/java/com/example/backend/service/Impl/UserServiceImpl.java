package com.example.backend.service.Impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.example.backend.common.BaseContext;
import com.example.backend.entity.dto.RegisterForm;
import com.example.backend.entity.dto.SendMessage;
import com.example.backend.entity.po.MyMessage;
import com.example.backend.entity.po.User;
import com.example.backend.entity.vo.MessageVO;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/*
 * @Auther:fz
 * @Date:2025/5/30
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;


    @Override
    public User loginCheck(String email, String password) {
        User user = userMapper.getUserByEmail(email);
        //密码校验
        boolean isOk = BCrypt.checkpw(password, user.getPassword());
        if(isOk)return user;
        else return null;
    }

    @Override
    public Integer register(RegisterForm registerForm) {
        //生成账号
        String account = IdUtil.getSnowflake(1, 1).nextIdStr();
        //密码加密
        String hashed = BCrypt.hashpw(registerForm.getPassword(), BCrypt.gensalt());
        User newUser = User.builder()
                .username(registerForm.getUsername())
                .account(account)
                .password(hashed)
                .avatar("2003346691566211072.jpg")
                .email(registerForm.getEmail())
                .time(new Date())
                .build();
        Integer status=userMapper.register(newUser);
        if (status==1) return 1;
        else return 0;
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public Integer InsertMessage(SendMessage sendMessage) {
        if (sendMessage.getToUserId()==null)sendMessage.setToUserId(0L);
        MyMessage message = MyMessage.builder()
                .context(sendMessage.getContext())
                .toUserId(sendMessage.getToUserId())
                .fromUserId(BaseContext.getCurrentId())
                .build();
        Integer status=userMapper.InsertMessage(message);
        if (status==1) return 1;
        else return 0;
    }

    @Override
    public List<MessageVO> getMessageByToUserId() {
        List<MyMessage>myMessages= userMapper.getMessageByToUserId(BaseContext.getCurrentId());
        return myMessages.stream().map(msg->{
            return MessageVO.builder()
                    .context(msg.getContext())
                    .username(userMapper.getUserById(msg.getFromUserId()).getUsername())
                    .build();
        }).toList();
    }
}
