package com.wangguolong.concurrency.example.syncContainer;

import com.wangguolong.concurrency.annotations.NotThreadSafe;

import java.util.Vector;

/**
 * 在使用同步容器的时候不一定都线程安全，在调用端还是需要做处理
 * 可能在get的时候的元素已经remove了
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/10 8:23 下午
 */
@NotThreadSafe
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }
}
