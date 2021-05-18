package com.epam.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private final static String LOGIN_PAGE = "index.jsp";
    private final static String ID = "id";
    private final static String COMMAND = "command";
    private final static String LOGIN_COMMAND = "login";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        String command = request.getParameter(COMMAND);
        if (session.getAttribute(ID) == null &&
                !command.equals(LOGIN_COMMAND)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_PAGE);
            dispatcher.forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
