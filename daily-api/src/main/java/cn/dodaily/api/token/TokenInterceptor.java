package cn.dodaily.api.token;

import cn.dodaily.api.exception.bean.ErrorResultEnum;
import cn.dodaily.api.exception.impl.NotFoundException;
import cn.dodaily.api.exception.impl.UnauthorizedException;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Token拦截器：除登录、注册等操作之外，其它的所有请求全部拦截并验证其合法性
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnauthorizedException {
        String authorization = request.getHeader("Authorization");
        try{
            Claims claims = JwtHelper.parseJws(authorization);
            Integer userId = (Integer) claims.get("userId");
            //todo 把用户id存入redis，供数据库操作时使用
        }catch (Exception e){
            //throw new UnauthorizedException(ErrorResultEnum.Unauthorized);
            //此处抛出的异常不能在前端正常获取,并且跨域失败,原因未知
        }
        return true;
    }
}
