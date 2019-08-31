package cn.dodaily.api.token;

import cn.dodaily.api.bean.user.User;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 登录之后，生成JWT格式的token字符串，并写入响应头文件(或存入cookie)
 * (拦截器不能正常修改@ResponseBody注解的Contreoller响应头)
 */
@ControllerAdvice
public class TokenAdvice implements ResponseBodyAdvice<User> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //仅在登录操作时该advice有效
        return "login".equals(methodParameter.getMethod().getName());
    }

    @Override
    public User beforeBodyWrite(User user, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        String authorization = JwtHelper.createJws(user);
        //将token放入Response头部
        serverHttpResponse.getHeaders().add("Authorization",authorization);
        return user;
    }
}
