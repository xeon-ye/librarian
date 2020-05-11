package com.nulijiushimeili.librarianwebui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 本网站的 api 允许跨域访问
 */
@Configuration
public class CorsConfig {

    public CorsConfig() {

    }

    /**
     * 设置跨域配置, 增加了这个配置以后就可以在其他网站使用前端js调用本服务的接口，获取数据
     * 其他服务如果想要配置关于本服务地址的全局变量，可以采用thymeleaf模板，直接在后端插值保存。
     * @return 跨域配置
     */
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        // 设置是否发送cookie信息
        config.setAllowCredentials(true);

        config.addAllowedMethod("*");
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);

        return new CorsFilter(corsSource);
    }


}
