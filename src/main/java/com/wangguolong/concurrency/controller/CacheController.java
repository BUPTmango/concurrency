package com.wangguolong.concurrency.controller;

import com.wangguolong.concurrency.example.cache.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/14 3:29 下午
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private RedisClient redisClient;

    @RequestMapping("/set")
    public String set(@RequestParam("key") String key,
                      @RequestParam("value") String value) throws Exception {
        redisClient.set(key, value);
        return "SUCCESS";
    }

    @RequestMapping("/get")
    public String get(@RequestParam("key") String key) throws Exception {
        return redisClient.get(key);
    }
}
