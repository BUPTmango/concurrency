package com.wangguolong.concurrency.example.singleton;

import com.wangguolong.concurrency.annotations.NotRecommend;
import com.wangguolong.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * 并不推荐 带来性能上的开销
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/10 10:56 上午
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    /**
     * 私有的构造函数
     */
    private SingletonExample3() {

    }

    /**
     * 单例对象
     */
    public static SingletonExample3 instance = null;

    /**
     * 静态的工厂方法
     * @return
     */
    public static synchronized SingletonExample3 getInstance() {
        // 有了synchronized， 在同一时间只会被一个线程访问
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
