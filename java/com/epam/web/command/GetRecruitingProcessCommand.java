package com.epam.web.command;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.service.RecruitingProcessService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetRecruitingProcessCommand implements Command {
    private final RecruitingProcessService service;
    private final static String ID = "id";
    private final static String PROCESS_ATTRIBUTE = "process";
    private final static String EMPTY_PAGE = "empty";

    public GetRecruitingProcessCommand(RecruitingProcessService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        String idParam = request.getParameter(ID);
        Long id = Long.parseLong(idParam);
        RecruitingProcess process = service.getById(id);
        request.setAttribute(PROCESS_ATTRIBUTE, process);
        return CommandResult.redirect(EMPTY_PAGE);
    }
}
