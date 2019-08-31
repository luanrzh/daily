package cn.dodaily.api.token;

import cn.dodaily.api.bean.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;


class JwtHelper {

    private static SecretKey key = Keys.hmacShaKeyFor( "123456789123456789123456789123456789".getBytes());

    /**
     * 创建JWS(JSON Web Signature)
     *
     * @param user 用户信息，将用户id存入claims
     * @return JWS格式的token字符串
     */
    static String createJws(User user) {
        return Jwts.builder().claim("userId", user.getId()).signWith(key).compact();
    }

    /**
     * @param jwsString JWS格式的token字符串
     * @return JWS body
     * @throws JwtException Jwt异常
     */
    static Claims parseJws(String jwsString) throws JwtException {
        Jws<Claims> jws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsString);
        return jws.getBody();
    }
}
