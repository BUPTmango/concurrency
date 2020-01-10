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
public class SynchronizedExample2 {

    /**
     * 修饰一个类 作用的是这个类的所有对象
     * @param j
     */
    public static void test1(int j) {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {} - {}", i, j);
            }
        }
    }

    /**
     * 修饰一个静态方法 作用的是这个类的所有对象
     * @param j
     */
    public static synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {} - {}", i, j);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test2(1);
        });
        executorService.execute(() -> {
            example2.test2(2);
        });
    }

}
