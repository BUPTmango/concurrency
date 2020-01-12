package com.wangguolong.concurrency.example.aqs;

import com.wangguolong.concurrency.annotations.Recommend;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Future的使用
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/12 10:56 上午
 */
@Slf4j
public class FutureExample {

    /**
     * 一般情况下是不可以用static修饰类的。如果一定要用static修饰类的话，通常static修饰的是匿名内部类。
     */
    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 用future接收了另外一个线程执行的结果
        Future<String> future = executorService.submit(new MyCallable());
        log.info("do something in main");
        Thread.sleep(1000);
        // 如果future对应的线程没有结束的话就会一直阻塞在这里
        String result = future.get();
        log.info("result: {}", result);
        executorService.shutdown();
    }
}
