package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllUsersCommand implements Command {

    private final UserService service;
    private final static String MAIN = "mainPage";
    private final static String USERS = "userList";

    public GetAllUsersCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        List<User> userList = service.getAll();
        request.setAttribute(USERS, userList);
        return CommandResult.redirect(MAIN);
    }
}
