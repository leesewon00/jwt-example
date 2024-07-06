package com.example.springjwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

        corsRegistry.addMapping("/**") // 모든 경로에서의 cors 허용
                .exposedHeaders("Set-Cookie") // 클라이언트에게 특정 헤더 노출 (토큰 꺼내와서 사용할 수 있게됨)
                .allowedOrigins("http://localhost:3000"); // localhost:3000에서만 모두 접근 허용
    }
}
