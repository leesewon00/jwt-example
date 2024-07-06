package com.example.springjwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    // JWT 토큰을 Redis에 저장하는 메서드
    @Transactional
    public void saveToken(String key, String token, long duration) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, token, Duration.ofMillis(duration));
    }

    // JWT 토큰을 Redis에서 조회하는 메서드
    @Transactional(readOnly = true)
    public String getToken(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // JWT 토큰을 Redis에서 삭제하는 메서드
    @Transactional
    public void deleteToken(String key) {
        redisTemplate.delete(key);
    }

    // JWT 토큰의 만료 시간을 설정하는 메서드
    @Transactional
    public void setTokenExpiry(String key, long durationInSeconds) {
        redisTemplate.expire(key, durationInSeconds, TimeUnit.SECONDS);
    }

    // JWT 토큰이 만료되었는지 확인하는 메서드
    @Transactional(readOnly = true)
    public boolean isTokenExpired(String key) {
        Long expireTime = redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
        return expireTime == null || expireTime <= 0;
    }

    @Transactional(readOnly = true)
    public Long getTokenExpiry(String key){
        return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
    }
}