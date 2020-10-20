package com.zj.redis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StringRedisTemplate 使用分析
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("StringRedisTemplateTest测试")
public class StringRedisTemplateStringTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 新增一个字符串类型的值,key是键，value是值。
     * set(K key, V value)
     */
    @Test
    @DisplayName("StringRedisTemplate测试1：opsForValue --> key/value类型使用")
    void test1() {
        stringRedisTemplate.opsForValue().set("string1","string2");
    }


}
