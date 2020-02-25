package com.wangguolong.concurrency.example.atomic;

import com.wangguolong.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/9 10:53 上午
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    /**
     * AtomicIntegerFieldUpdater更新某一个类的特定字段的值
     */
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    /**
     * 使用在AtomicIntegerFieldUpdater中的字段必须用volatile进行标识
     */
    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {
        // 里面包含了count的值为100
        AtomicExample5 example5 = new AtomicExample5();
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 1, {}", example5.getCount());
        }
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 2, {}", example5.getCount());
        } else {
            log.info("update fail, {}", example5.getCount());
        }
    }
}
