package com.wangguolong.concurrency.util;

import com.wangguolong.concurrency.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/10 5:45 下午
 */
@Slf4j
public class HttpFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 通常使用HttpServletRequest 进行强制转换就可以
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("do filter, {}, {}", Thread.currentThread().getId(), request.getServletPath());
        RequestHolder.add(Thread.currentThread().getId());
        // 保证请求继续被处理
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
