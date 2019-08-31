package cn.dodaily.api.config;

import cn.dodaily.api.token.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 自定义参数绑定
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new String2DateConverter());
    }

    /**
     * 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //增加token拦截器
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/user/*");
    }

    /**
     * String-Date转换器
     */
    private static class String2DateConverter implements Converter<String, Date> {
        @Override
        public Date convert(String s) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
