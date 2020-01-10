package com.wangguolong.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/9 1:28 下午
 */
@Slf4j
public class SynchronizedExample1 {

    /**
     * 修饰一个代码块 作用的是当前对象 不同调用对象之间是不影响的
     * @param j
     */
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {} - {}", i, j);
            }
        }
    }

    /**
     * 修饰一个方法 作用的是调用对象 不同调用对象之间是不影响的
     * @param j
     */
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {} - {}", i, j);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test1(1);
        });
        executorService.execute(() -> {
            example2.test1(2);
        });
    }

}
