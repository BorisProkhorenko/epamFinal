package com.epam.web.command;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.Response;
import com.epam.web.entity.User;
import com.epam.web.enums.Role;
import com.epam.web.service.ResponseService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetResponseCommand implements Command {
    private final ResponseService service;
    private final static String ID = "id";
    private final static String RESPONSE_ATTRIBUTE = "jobResponse";
    private final static String EMPTY_PAGE = "empty";
    private final static String ROLE = "role";


    public GetResponseCommand(ResponseService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        String idParam = request.getParameter(ID);
        Long id = Long.parseLong(idParam);
        Response jobResponse = service.getById(id);
        RecruitingProcess process = jobResponse.getRecruitingProcess();
        User user = process.getUser();
        HttpSession session = request.getSession();
        if (session.getAttribute(ROLE) == Role.APPLICANT &&
                session.getAttribute(ID) != user.getId()) {
            throw new ServiceException("Not available response for actual user");
        }
        request.setAttribute(RESPONSE_ATTRIBUTE, jobResponse);
        return CommandResult.redirect(EMPTY_PAGE);
    }
}
