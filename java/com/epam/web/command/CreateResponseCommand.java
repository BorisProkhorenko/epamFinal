package com.epam.web.command;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.Response;
import com.epam.web.service.RecruitingProcessService;
import com.epam.web.service.ResponseService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class CreateResponseCommand implements Command {

    private final ResponseService responseService;
    private final RecruitingProcessService processService;
    private static final String RESPONSES = "responses";
    private static final Long MOCK_ID = 0L;
    private static final Date MOCK_DATE = null;
    private static final String SUBJECT = "subject";
    private static final String DETAILS = "details";
    private static final String PROCESS_ID = "processId";

    public CreateResponseCommand(ResponseService responseService, RecruitingProcessService processService) {
        this.responseService = responseService;
        this.processService = processService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        String subject = request.getParameter(SUBJECT);
        String details = request.getParameter(DETAILS);
        HttpSession session = request.getSession();
        String idParam = (String) session.getAttribute(PROCESS_ID);
        Long recruitingProcessId = Long.parseLong(idParam);
        RecruitingProcess process = processService.getById(recruitingProcessId);
        Response jobResponse = new Response(MOCK_ID, subject, details, MOCK_DATE, process);
        responseService.update(jobResponse);
        return CommandResult.redirect(RESPONSES);
    }
}
