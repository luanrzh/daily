package cn.dodaily.api.token;

import cn.dodaily.api.bean.user.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class JwtHelperTest {

    @Test
    public void createJws() {
        User user = new User();
        user.setId(123);
        JwtHelper.createJws(user);
    }

    @Test
    public void parseJws() {
        String jwsString = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEyM30.IW69hu36gfpg7P1ddQ_0OaGHp8oPjqLgOCmCXh_tFCk";
        JwtHelper.parseJws(jwsString);

    }
}