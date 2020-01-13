package com.wangguolong.concurrency.example.threadPool;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPool
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/13 10:54 上午
 */
@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {

        /**
         * 执行结果是按顺序来的 不会是乱序的
         */
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

/*        executorService.schedule(() -> {
            log.warn("schedule run");
        }, 3, TimeUnit.SECONDS); // 设置延迟三秒后执行*/

        executorService.scheduleAtFixedRate(() -> {
            log.warn("schedule run");
        }, 1, 3, TimeUnit.SECONDS); // 延迟一秒后，每隔三秒执行一次任务
//        executorService.shutdown();

        // 使用ScheduledExecutorService代替Timer吧
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        }, new Date(), 5 * 1000);

    }
}
