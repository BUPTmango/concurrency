package com.wangguolong.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch的另一个用法
 * 在规定的时间完成，要是超出时间了，能运行多少算多少
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/11 2:47 下午
 */
@Slf4j
public class CountDownLatchExample2 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        // await可以传入等待时间
        // 给定时间也能防止忘记调用countDown()而造成的死等待
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        // shutdown等待当前的线程全部执行完之后将线程池关闭
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", threadNum);
    }
}
