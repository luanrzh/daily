package cn.dodaily.api.token;

import cn.dodaily.api.exception.bean.ErrorResultEnum;
import cn.dodaily.api.exception.impl.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Token拦截器：除登录、注册等操作之外，其它的所有请求全部拦截并验证其合法性
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        try{
            Claims claims = JwtHelper.parseJws(authorization);
            Integer userId = (Integer) claims.get("userId");
            //把用户id存入当前用户会话，供数据库操作时使用
            request.getSession().setAttribute("userId",userId);
        }catch (JwtException e){
            throw new UnauthorizedException(ErrorResultEnum.Unauthorized);
        }
        return false;
    }
}
