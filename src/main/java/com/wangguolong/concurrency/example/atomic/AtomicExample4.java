package com.wangguolong.concurrency.example.atomic;

import com.wangguolong.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference类提供了一个可以原子读写的对象引用变量
 *  AtomicReference甚至有一个先进的compareAndSet（）方法，
 *  它可以将引用与预期值（引用）进行比较，如果它们相等，则在AtomicReference对象内设置一个新的引用
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/9 10:53 上午
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0,2);
        count.compareAndSet(0,1);
        count.compareAndSet(1,3);
        count.compareAndSet(2,4);
        count.compareAndSet(3,5);
        log.info("count:{}", count.get());
    }
}
