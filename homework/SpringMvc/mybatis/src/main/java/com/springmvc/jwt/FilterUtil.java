package com.springmvc.jwt;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebFilter(urlPatterns = "/*",filterName = "filterUtil")
public class FilterUtil implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1. 解决请求乱码（获取表单数据前的关键步骤）
        servletRequest.setCharacterEncoding("UTF-8");

        // 2. 解决响应乱码
        servletResponse.setCharacterEncoding("UTF-8");
        // 如果你还在用 JSP，不要在这里强行设置 application/json，否则 JSP 页面可能显示异常
        // servletResponse.setContentType("text/html;charset=utf-8");

        // 3. 跨域配置（保持你原有的逻辑）
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        // 4. 继续执行后续逻辑
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}