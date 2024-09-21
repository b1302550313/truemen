// redis缓存服务，需要配置依赖
// <dependency>
// <groupId>org.springframework.boot</groupId>
// <artifactId>spring-boot-starter-data-redis</artifactId>
// </dependency>
// 在 application.properties  中添加 Redis 配置：
// spring.redis.host=localhost
// spring.redis.port=6379


// package com.sysu.verto.user.service;

// import org.springframework.data.redis.core.StringRedisTemplate;
// import org.springframework.stereotype.Service;

// import java.time.Duration;

// @Service
// public class CacheService {
//     private final StringRedisTemplate redisTemplate;

//     public CacheService(StringRedisTemplate redisTemplate) {
//         this.redisTemplate = redisTemplate;
//     }

//     public void set(String key, String value, Duration duration) {
//         redisTemplate.opsForValue().set(key, value, duration);
//     }

//     public String get(String key) {
//         return redisTemplate.opsForValue().get(key);
//     }

//     public void delete(String key) {
//         redisTemplate.delete(key);
//     }
// }
