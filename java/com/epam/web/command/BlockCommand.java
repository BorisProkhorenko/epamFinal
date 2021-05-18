package com.epam.web.command;

import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BlockCommand implements Command {

    private final UserService service;
    private final static String ID = "id";
    private final static String IS_BLOCKED = "isBlocked";
    private final static String MAIN = "mainPage";

    public BlockCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        String idParam = request.getParameter(ID);
        Long id = Long.parseLong(idParam);
        String isBlockedParam = request.getParameter(IS_BLOCKED);
        boolean isBlocked = Boolean.parseBoolean(isBlockedParam);
        service.changeBlock(id, isBlocked);
        return CommandResult.redirect(MAIN);
    }
}
