package com.wangguolong.concurrency.controller;

import com.wangguolong.concurrency.example.threadLocal.RequestHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/10 7:06 下午
 */
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @RequestMapping("/test")
    public Long test() {
        return RequestHolder.getId();
    }
}
