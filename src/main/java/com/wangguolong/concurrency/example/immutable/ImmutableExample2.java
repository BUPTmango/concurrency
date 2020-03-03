package com.wangguolong.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.wangguolong.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * Collections.unmodifiableMap
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/10 4:08 下午
 */
@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        // 使用了Collections.unmodifiableMap，再进行put会报异常
        map.put(1, 3);
        log.info("{}", map.get(1));
    }
}
