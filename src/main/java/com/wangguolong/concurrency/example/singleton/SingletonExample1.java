package com.wangguolong.concurrency.example.singleton;

import com.wangguolong.concurrency.annotations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * 单线程下不会有问题 但是多线程在30-32行会出现问题
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/10 10:56 上午
 */
@NotThreadSafe
public class SingletonExample1 {

    /**
     * 私有的构造函数
     */
    private SingletonExample1() {

    }

    /**
     * 单例对象
     */
    public static SingletonExample1 instance = null;

    /**
     * 静态的工厂方法
     * @return
     */
    public static SingletonExample1 getInstance() {
        // 如果两个线程同时执行这里，对象会被创建两次
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
