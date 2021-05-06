package com.epam.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class SessionLocaleFilter implements Filter {

    private static final String LOCALE = "javax.servlet.jsp.jstl.fmt.locale.session";
    private static final String LANG = "lang";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getParameter(LOCALE) != null) {
            req.getSession().setAttribute(LANG, req.getParameter(LOCALE));
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) {
    }
}



