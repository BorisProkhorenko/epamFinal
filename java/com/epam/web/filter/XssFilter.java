package com.epam.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class XssFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) {
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        chain.doFilter(new XssRequestWrapper(request), servletResponse);
    }

    @Override
    public void destroy() {
    }
}
