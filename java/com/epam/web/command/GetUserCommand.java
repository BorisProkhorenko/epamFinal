package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserCommand implements Command{

    private final UserService service;
    private final static String ID="id";
    private final static String USER_ATTRIBUTE="user";
    private final static String EMPTY_PAGE="empty";

    public GetUserCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        String idParam =  request.getParameter(ID);
        Long id = Long.parseLong(idParam);
        User user = service.getById(id);
        request.setAttribute(USER_ATTRIBUTE, user);
        return CommandResult.redirect(EMPTY_PAGE);
    }
}
