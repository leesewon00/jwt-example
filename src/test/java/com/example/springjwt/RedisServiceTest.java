package com.example.springjwt;

import com.example.springjwt.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisServiceTest {

    @Autowired
    RedisService redisService;

    @Test
    void redis_saveToken() {
        redisService.saveToken("test", "1234", 86400000L);
    }

    @Test
    void redis_deleteToken(){
        redisService.deleteToken("test");
    }

    @Test
    void redis_setTokenExpiry(){
        redisService.setTokenExpiry("test",0L);
    }

    @Test
    void redis_getTokenExpiry(){
        Long test = redisService.getTokenExpiry("test");
        System.out.println(test);
    }

    @Test
    void redis_isTokenExpired(){
        boolean test = redisService.isTokenExpired("test");
        System.out.println(test);
    }
}
