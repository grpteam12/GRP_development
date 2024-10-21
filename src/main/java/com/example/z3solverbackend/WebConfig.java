package com.example.z3solverbackend; // 请根据你的项目实际包名修改

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 允许所有路径
                        .allowedOrigins("http://localhost:8080") // 前端地址，包含端口号
                        .allowedMethods("*") // 允许所有HTTP方法
                        .allowedHeaders("*") // 允许所有请求头
                        .allowCredentials(true); // 允许发送凭证（如Cookie）
            }
        };
    }
}
