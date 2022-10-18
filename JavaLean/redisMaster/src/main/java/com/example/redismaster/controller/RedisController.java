package com.example.redismaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mrl
 * @data 2022/7/18
 */
@Controller
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/redis/get/{key}")
    @ResponseBody
    public Object get(@PathVariable("key")String key){
        System.out.println("get----------");
        Object o = redisTemplate.opsForValue().get(key);
        System.out.println(o);
        return o;
    }

    @PostMapping("/redis/set/{key}/{value}")
    @ResponseBody
    public Object set(@PathVariable("key")String key,
                      @PathVariable("value")String value){
        redisTemplate.opsForValue().set(key, value);
        return "success";
    }

}
