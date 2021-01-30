package com.gupaoedu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/first")
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("------first filter init----");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Before First filter execution");
        chain.doFilter(request, response);
        System.out.println("After First filter execution");
    }

    @Override
    public void destroy() {
        System.out.println("--------first filter destroy-------");
    }
}
