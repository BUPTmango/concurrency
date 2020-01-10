package com.wangguolong.concurrency.example.singleton;

import com.wangguolong.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 * 有两个要求：
 * 1. 私有构造函数不能有太多的处理
 * 2. 这个类在实际使用中肯定会被使用，不会带来资源的浪费
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/10 10:56 上午
 */
@ThreadSafe
public class SingletonExample2 {

    /**
     * 私有的构造函数
     */
    private SingletonExample2() {

    }

    /**
     * 单例对象
     */
    public static SingletonExample2 instance = new SingletonExample2();

    /**
     * 静态的工厂方法
     * @return
     */
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
