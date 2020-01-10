package com.wangguolong.concurrency.example.singleton;

import com.wangguolong.concurrency.annotations.Recommend;
import com.wangguolong.concurrency.annotations.ThreadSafe;

/**
 * 枚举模式---最安全
 * 推荐的理由：
 * 1. 相比懒汉模式在安全性更容易保证
 * 2. 相比饿汉模式在实际调用的时候才进行初始化，不会造成资源的浪费
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/10 10:56 上午
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    /**
     * 私有的构造函数
     */
    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        /**
         * 实例
         */
        INSTANCE;

        private SingletonExample7 singleton;

        /**
         * JVM保证这个方法绝对只调用一次
         */
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }

}
