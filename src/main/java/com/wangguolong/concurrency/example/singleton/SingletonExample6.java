package com.wangguolong.concurrency.example.singleton;

import com.wangguolong.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建 也可以使用静态代码块的方式实现
 * 有两个要求：
 * 1. 私有构造函数不能有太多的处理
 * 2. 这个类在实际使用中肯定会被使用，不会带来资源的浪费
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/10 10:56 上午
 */
@ThreadSafe
public class SingletonExample6 {

    /**
     * 私有的构造函数
     */
    private SingletonExample6() {

    }

    /**
     * 单例对象
     */
    public static SingletonExample6 instance = null;

    // 静态代码块的顺序要注意 要在变量的下方 顺序不能颠倒
    static {
        instance = new SingletonExample6();
    }

    /**
     * 静态的工厂方法
     * @return
     */
    public static SingletonExample6 getInstance() {
        return instance;
    }
}
