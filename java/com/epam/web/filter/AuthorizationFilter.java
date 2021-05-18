package com.epam.web.filter;

import com.epam.web.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class AuthorizationFilter implements Filter {

    private final Map<Role, List<String>> roleMap = new HashMap<>();
    private final static String ERROR_PAGE = "error.jsp";
    private final static String LOGIN = "login";
    private final static String INVALID_LOGIN = "invalidLogin";
    private final static String MAIN = "mainPage";
    private final static String CV = "cv";
    private final static String RESPONSES = "responses";
    private final static String RESPONSE_DETAILS = "responseDetails";
    private final static String LOGOUT = "logout";
    private final static String VACANCY = "vacancy";
    private final static String EDIT_CV = "editCv";
    private final static String UPDATE_CV = "updateCv";
    private final static String EDIT_VACANCY = "editVacancy";
    private final static String UPDATE_VACANCY = "updateVacancy";
    private final static String CREATE_VACANCY = "createVacancy";
    private final static String DELETE_VACANCY = "deleteVacancy";
    private final static String CREATE_RESPONSE = "createResponse";
    private final static String UPDATE_RESPONSE = "updateResponse";
    private final static String PHOTO = "photo";
    private final static String EMPTY = "empty";
    private final static String VACANCY_LIST = "vacancyList";
    private final static String RESPONSE_LIST = "responseList";
    private final static String USER_LIST = "userList";
    private final static String GET_VACANCY = "getVacancy";
    private final static String GET_USER = "getUser";
    private final static String GET_RESPONSE = "getResponse";
    private final static String GET_RECRUITING_PROCESS = "getProcess";
    private final static String IMAGE = "image";
    private final static String APPLICANTS = "applicants";
    private final static String APPLICANT_LIST = "applicantList";
    private final static String APPLY = "apply";
    private final static String FEEDBACK = "feedback";
    private final static String UPDATE_RESPONSE_PROCESS = "updateResponseAndProcess";
    private final static String UPDATE_PROCESS = "updateProcess";
    private final static String BLOCK = "block";

    private final static String COMMAND = "command";
    private final static String ROLE = "role";

    @Override
    public void init(FilterConfig filterConfig) {
        List<String> adminCommands = Arrays.asList(LOGOUT, USER_LIST, BLOCK, MAIN, EMPTY);
        roleMap.put(Role.ADMIN, adminCommands);

        List<String> applicantCommands = Arrays.asList(LOGOUT, MAIN, CV, RESPONSES, RESPONSE_DETAILS, VACANCY, EDIT_CV,
                UPDATE_CV, PHOTO, APPLY, IMAGE, EMPTY, VACANCY_LIST, RESPONSE_LIST, GET_USER, GET_RESPONSE,
                GET_VACANCY, GET_RECRUITING_PROCESS);
        roleMap.put(Role.APPLICANT, applicantCommands);

        List<String> hrCommands = Arrays.asList(LOGOUT, MAIN, CV, RESPONSES, RESPONSE_DETAILS, VACANCY, EDIT_VACANCY,
                UPDATE_VACANCY, IMAGE, EMPTY, CREATE_VACANCY, DELETE_VACANCY, CREATE_RESPONSE, UPDATE_RESPONSE,
                UPDATE_RESPONSE_PROCESS, VACANCY_LIST, RESPONSE_LIST, GET_VACANCY, GET_USER, UPDATE_PROCESS, FEEDBACK,
                GET_RESPONSE, APPLICANTS, APPLICANT_LIST, GET_RECRUITING_PROCESS);
        roleMap.put(Role.HR, hrCommands);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String command = request.getParameter(COMMAND);
        if (command.equals(LOGOUT) ||
                command.equals(LOGIN) ||
                command.equals(INVALID_LOGIN)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        Role role = (Role) session.getAttribute(ROLE);
        List<String> commandList = roleMap.get(role);
        for (String availableCommand : commandList) {
            if (availableCommand.equals(command)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
        dispatcher.forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
