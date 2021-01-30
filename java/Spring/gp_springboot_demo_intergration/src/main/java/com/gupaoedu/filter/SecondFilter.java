package com.gupaoedu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("------second filter init----");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Before second filter execution");
        chain.doFilter(request, response);
        System.out.println("After second filter execution");
    }

    @Override
    public void destroy() {
        System.out.println("--------second filter destroy-------");
    }
}
