package com.wangguolong.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore的进阶用法
 * 每次拿到多个许可
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/11 2:47 下午
 */
@Slf4j
public class SemaphoreExample2 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(20);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    // 获取5个许可
                    // 就相当于同时有 20 / 5 = 4 个线程并发执行
                    semaphore.acquire(5);
                    test(threadNum);
                    // 释放5个许可
                    semaphore.release(5);
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);

    }
}
