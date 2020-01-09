package com.wangguolong.concurrency.example.atomic;

import com.wangguolong.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/9 10:53 上午
 */
@Slf4j
@ThreadSafe
public class AtomicExample6 {

    // 请求总数
    public static int clientTotal = 5000;
    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static AtomicBoolean isHappened = new AtomicBoolean(false);


    public static void main(String[] args) throws Exception {
        // 首先定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 定义计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        // 把请求全部放入线程池中
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    // 判断当前线程能否被执行
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception : ", e);
                }
                // 没执行完一个之后调用一次countDown, 计数值就会减一个
                countDownLatch.countDown();
            });
        }
        // 保证计数器为0
        countDownLatch.await();
        executorService.shutdown();
        log.info("isHappened : {}", isHappened.get());
    }

    private static void test() {
        // 只有一个线程能够执行成功，所以log也只会输出一次
        if (isHappened.compareAndSet(false, true)) {
            log.info("execute");
        }
    }
}
