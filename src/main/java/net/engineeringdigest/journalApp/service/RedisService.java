//package net.engineeringdigest.journalApp.service;
//
//import io.lettuce.core.RedisClient;
//import io.lettuce.core.RedisURI;
//import io.lettuce.core.api.StatefulRedisConnection;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.stereotype.Service;
//
//import java.awt.print.Book;
//
//@Configuration
//public class RedisService {
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory();
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory());
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        return template;
//    }
//
////    @Bean("lettuceConnectionFactory")
////    @Profile("dev")
////    LettuceConnectionFactory productionLettuceConnectionFactory() {
////        LettuceConnectionFactory factory = new LettuceConnectionFactory();
////        factory.setHostName(redisHostName);
////        factory.setPort(Integer.parseInt(port));
////        factory.setPassword(password);
////        factory.setUseSsl(true);
////        return factory;
////    }
//
////    @Bean
////    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
////        RedisTemplate<String, String> template = new RedisTemplate<>();
////        template.setConnectionFactory(connectionFactory);
////        // Add some specific configuration here. Key serializers, etc.
////        return template;
////    }
////    @Bean
////    RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
////        RedisTemplate redisTemplate = new RedisTemplate<>();
////        redisTemplate.setConnectionFactory(redisConnectionFactory);
////        redisTemplate.setKeySerializer(new StringRedisSerializer());
////        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
////        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
////        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
////        return redisTemplate;
////    }
//
//}
