package cn.dodaily.api.token;

import cn.dodaily.api.bean.user.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class JwtHelperTest {

    @Test
    public void createJws() {
        User user = new User();
        user.setId(1);
        String jws = JwtHelper.createJws(user);
    }

    @Test
    public void parseJws() {
        String jwsString = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.nTn4zGsREcWn1U_9Z70CsbWYISaZ80TrKBmEKrHAmVI";
        JwtHelper.parseJws(jwsString);

    }
}