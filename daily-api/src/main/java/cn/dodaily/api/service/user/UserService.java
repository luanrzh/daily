package cn.dodaily.api.service.user;

import cn.dodaily.api.bean.user.User;
import cn.dodaily.api.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User login(User user){
        return userMapper.selectUser(user);
    }
}
