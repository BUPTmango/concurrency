package com.wangguolong.concurrency.example.singleton;

import com.wangguolong.concurrency.annotations.NotRecommend;
import com.wangguolong.concurrency.annotations.NotThreadSafe;
import com.wangguolong.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式 -》 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/10 10:56 上午
 */
@NotThreadSafe
public class SingletonExample4 {

    /**
     * 私有的构造函数
     */
    private SingletonExample4() {

    }

    /**
     * 单例对象
     */
    public static SingletonExample4 instance = null;

    /**
     * 静态的工厂方法
     *
     * instance = new SingletonExample4()
     * 1. memory = allocate() 分配对象的内存空间
     * 2. ctorInstance() 初始化对象
     * 3. instance = memory 设置instance指向刚分配的内存
     * 单线程没有影响，但是多线程会出现指令重排
     *
     * JVM和CPU优化，发生了指令重排
     * 1. memory = allocate() 分配对象的内存空间
     * 3. instance = memory 设置instance指向刚分配的内存
     * 2. ctorInstance() 初始化对象
     * @return
     */
    public static SingletonExample4 getInstance() {
        // 使用双层检测机制
        if (instance == null) {                       // B
            // 同步锁
            synchronized (SingletonExample4.class) {
                if (instance == null) {
                    instance = new SingletonExample4(); // A - 3
                }
            }
        }
        // A线程执行了3但是没有执行2的时候，B发现不为null，就返回了对象，但实际上A还没有执行2初始化对象，这时候B使用对象就会出现异常
        return instance;
    }
}
