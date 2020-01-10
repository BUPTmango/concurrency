package com.wangguolong.concurrency.example.publish;

import com.wangguolong.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 对象在未完成构造时不可以进行发布
 * 可以使用工厂方法
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/10 10:34 上午
 */
@Slf4j
@NotThreadSafe
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
