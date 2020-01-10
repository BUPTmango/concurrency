package com.wangguolong.concurrency.example.threadLocal;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/10 5:36 下午
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    /**
     * 添加信息 在请求进入后端服务器但是没有实际处理的时候调用
     * filter 过滤器
     * @param id
     */
    public static void add(Long id) {
        // key是当前线程的id，value是传入的id
        requestHolder.set(id);
    }

    /**
     * 取出内容的方法
     * @return
     */
    public static Long getId() {
        return  requestHolder.get();
    }

    /**
     * 移除当前变量信息 否则会内存泄漏
     * 处理完接口之后调用
     * interceptor 拦截器
     */
    public static void remove() {
        requestHolder.remove();
    }
}
