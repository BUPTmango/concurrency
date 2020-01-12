package com.wangguolong.concurrency.example.aqs;

import com.wangguolong.concurrency.annotations.Recommend;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * FutureTask的使用
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/12 11:27 上午
 */
@Slf4j
@Recommend
public class FutureTaskExample {

    public static void main(String[] args) throws Exception {

        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });

        new Thread(futureTask).start();
        log.info("do something in main");
        Thread.sleep(1000);
        String result = futureTask.get();
        log.info("result: {}", result);
    }
}
