package net.engineeringdigest.journalApp.service;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.awt.print.Book;


@SpringBootTest
public class RedisTests {
//    @Autowired
//    private RedisService redisService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    @Test
    void testSendMail() {
        redisTemplate.opsForValue().set("email","gmail@email.com");
        Object salary = redisTemplate.opsForValue().get("salary");
        int a = 1;
    }
}
