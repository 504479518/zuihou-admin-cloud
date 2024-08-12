package top.tangyh.lamp.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.tangyh.lamp.common.properties.IgnoreProperties;
import top.tangyh.lamp.common.properties.SystemProperties;
import top.tangyh.lamp.common.satoken.HeaderThreadLocalInterceptor;

/**
 * 公共配置类, 一些公共工具配置
 *
 * @author zuihou
 * @date 2018/8/25
 */
@RequiredArgsConstructor
public class GlobalMvcConfigurer implements WebMvcConfigurer {
    private final IgnoreProperties ignoreProperties;
    private final SystemProperties systemProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HeaderThreadLocalInterceptor(ignoreProperties))
                .addPathPatterns("/**")
                .order(-20);
        registry.addInterceptor(new NotAllowWriteInterceptor(systemProperties))
                .addPathPatterns("/**")
                .order(Integer.MIN_VALUE);
    }
}
