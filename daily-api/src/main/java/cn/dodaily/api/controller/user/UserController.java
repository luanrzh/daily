package cn.dodaily.api.controller.user;

import cn.dodaily.api.bean.user.User;
import cn.dodaily.api.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public User login(User user) {
        return userService.login(user);
    }
}
