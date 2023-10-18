package com.hkjava.demo.demofinnhub.infra;

import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.client.ResourceAccessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class RedisHelper {

    // key value pair, key must be unqiue, most likely String
    private RedisTemplate<String, Object> redisTemplate;

    public RedisHelper(RedisConnectionFactory factory) {
        ObjectMapper objectMapper = new ObjectMapper() //
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module()) //
                .registerModule(new JavaTimeModule());
        this.redisTemplate = template(factory, objectMapper);
    }

    public static RedisTemplate<String, Object> template(
            RedisConnectionFactory factory, ObjectMapper redisObjectMapper) { // method name -> bean name
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
        // redisTemplate.setHashKeySerializer(RedisSerializer.string());
        // redisTemplate.setHashValueSerializer(RedisSerializer.json());
        redisTemplate.afterPropertiesSet();
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(redisObjectMapper,
                Object.class);
        redisTemplate.setValueSerializer(serializer);
        return redisTemplate;
    }

    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            throw new ResourceAccessException("Redis unavailable.");
        }
    }

    public boolean set(String key, Object value, long time) {
        try {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
            return true;
        } catch (Exception e) {
            throw new ResourceAccessException(
                    "Redis unavailable (set method) msg=" + e.getMessage());
        }
    }

    public Object get(String key) {
        try {
            if (key != null) {
                Object object = redisTemplate.opsForValue().get(key);
                return object;
            }
            return null;
        } catch (Exception e) {
            throw new ResourceAccessException(
                    "Redis unavailable (get method) msg=" + e.getMessage());
        }
    }

    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new ResourceAccessException("Redis unavailable.");
        }
    }

    public static String formatKey(String head, String source) {
        return head.concat(":").concat(source);
    }
}
