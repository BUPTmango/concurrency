package com.wangguolong.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition
 * 很少使用
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/11 5:54 下午
 */
@Slf4j
public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock(); // 进入aqs等待队列中
                log.info("wait signal"); // 1
                condition.await(); // 进入condition的等待队列中
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); // 4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock(); // 由于线程1释放了锁，线程2获取到了
            log.info("get lock"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll(); // 发送信号
            log.info("send signal ~ "); // 3
            reentrantLock.unlock();
        }).start();
    }
}