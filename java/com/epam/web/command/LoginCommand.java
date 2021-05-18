package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.enums.Role;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private final UserService service;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ID = "id";
    private static final String ROLE = "role";
    private static final String PAGE = "pageIndex";
    private static final String DEFAULT_PAGE = "1";
    private static final String MAIN = "mainPage";
    private static final String INVALID = "invalidLogin";
    private final static String ERROR_MESSAGE = "errorMessage";
    private final static String BLOCKED = "blocked";

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        Optional<User> optionalUser = service.login(username, password);
        HttpSession session = request.getSession();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getIsBlocked()) {
                session.setAttribute(ERROR_MESSAGE, BLOCKED);
                return CommandResult.redirect(INVALID);
            }
            Long id = user.getId();
            Role role = user.getRole();
            session.setAttribute(ID, id);
            session.setAttribute(ROLE, role);
            session.setAttribute(PAGE, DEFAULT_PAGE);
            return CommandResult.redirect(MAIN);
        } else {
            session.setAttribute(ERROR_MESSAGE, INVALID);
            return CommandResult.redirect(INVALID);
        }
    }


}
