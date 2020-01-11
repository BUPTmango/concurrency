package com.wangguolong.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore的进阶用法
 * 超过限制就丢弃
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/11 2:47 下午
 */
@Slf4j
public class SemaphoreExample3 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(20);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    // tryAcquire 尝试获取一个许可
                    // 拿不到许可就直接丢弃掉
                    if (semaphore.tryAcquire()) {
                        test(threadNum);
                        // 释放一个许可
                        semaphore.release();
                    }
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
