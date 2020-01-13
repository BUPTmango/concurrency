package com.wangguolong.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SingleThreadExecutor
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/13 10:54 上午
 */
@Slf4j
public class ThreadPoolExample3 {

    public static void main(String[] args) {

        /**
         * 执行结果是按顺序来的 不会是乱序的
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                log.info("task:{}", index);
            });
        }

        executorService.shutdown();
    }
}
