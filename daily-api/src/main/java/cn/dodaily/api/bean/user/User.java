package cn.dodaily.api.bean.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //id
    private int id;
    //用户名
    private String username;
    //密码
    private String password;
}
