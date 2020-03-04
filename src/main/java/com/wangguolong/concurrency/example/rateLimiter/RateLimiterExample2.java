package com.wangguolong.concurrency.example.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class  RateLimiterExample2 {

    // 创建一个限流器，参数代表每秒生成的令牌数
    private static RateLimiter rateLimiter = RateLimiter.create(5);

    public static void main(String[] args) throws Exception {

        for (int index = 0; index < 100; index++) {
            // 以阻塞的方式获取令牌
            rateLimiter.acquire();
            handle(index);
        }
    }

    private static void handle(int i) {
       log.info("{}", i);
    }
}
