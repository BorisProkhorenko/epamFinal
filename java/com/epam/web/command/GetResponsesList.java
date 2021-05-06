package com.epam.web.command;

import com.epam.web.entity.Response;
import com.epam.web.enums.Role;
import com.epam.web.service.ResponseService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetResponsesList implements Command {

    private final ResponseService service;
    private final static String ID = "id";
    private final static String RESPONSE_LIST_ATTRIBUTE = "responseList";
    private final static String RESPONSES = "responses";
    private final static String ROLE = "role";

    public GetResponsesList(ResponseService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession();
        Long id;
        Role role = (Role) session.getAttribute(ROLE);
        List<Response> responseList;
        if (role.equals(Role.APPLICANT)) {
            id = (Long) session.getAttribute(ID);
            responseList = service.getResponsesByUserId(id);
        } else {
            String idParam = request.getParameter(ID);
            id = Long.parseLong(idParam);
            responseList = service.getResponsesByProcessId(id);
        }
        request.setAttribute(RESPONSE_LIST_ATTRIBUTE, responseList);
        return CommandResult.redirect(RESPONSES);
    }
}
