package com.wangguolong.concurrency.example.commonUnsafe;

import com.wangguolong.concurrency.annotations.Recommend;
import com.wangguolong.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * jodatime
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/10 7:26 下午
 */
@Slf4j
@ThreadSafe
@Recommend
public class DateFormatExample3 {

    /**
     * 添加joda-time依赖
     */
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    // 请求总数
    public static int clientTotal = 5000;
    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static void main(String[] args) throws Exception {
        // 首先定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 定义计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        // 把请求全部放入线程池中
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    // 判断当前线程能否被执行
                    semaphore.acquire();
                    update(count);
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
    }

    private static void update(int i) {
        log.info("{}, {}", i, DateTime.parse("2018-02-08", dateTimeFormatter).toDate());
    }
}
