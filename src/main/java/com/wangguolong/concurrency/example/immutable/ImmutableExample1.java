package com.wangguolong.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.wangguolong.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * final的HashMap里面的元素可以被修改
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/10 4:08 下午
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        // a = 2; 不可修改
        // b = "3"; 不可修改

        // map = Maps.newHashMap();  不能指向新的对象
        // 但是可以修改里面的值
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

    /**
     * final可以修饰方法传入的参数
     * @param a
     */
    private void test(final int a) {
        // a = 1;  也是不允许修改的
    }
}
