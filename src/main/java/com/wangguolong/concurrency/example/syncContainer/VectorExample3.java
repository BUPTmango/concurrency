package com.wangguolong.concurrency.example.syncContainer;

import com.wangguolong.concurrency.annotations.NotThreadSafe;

import java.util.Iterator;
import java.util.Vector;

/**
 * 建议是使用foreach和iterator进行遍历的时候
 * 不要在遍历过程中删除元素
 * 可以做好标记，在遍历完成后进行处理
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/10 8:23 下午
 */
public class VectorExample3 {

    /**
     * foreach
     * java.util.ConcurrentModificationException
     * @param v1
     */
    private static void test1(Vector<Integer> v1) {
        for (Integer i : v1) {
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    /**
     * iterator
     * java.util.ConcurrentModificationException
     * @param v1
     */
    private static void test2(Vector<Integer> v1) {
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    /**
     * for
     * success
     * @param v1
     */
    private static void test3(Vector<Integer> v1) {
        for (int i = 0; i < v1.size(); i++) {
            if (v1.get(i).equals(3)) {
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        test3(vector);
    }
}
