package com.epam.web.command;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.User;
import com.epam.web.entity.Vacancy;
import com.epam.web.enums.ApplicantState;
import com.epam.web.service.RecruitingProcessService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateRecruitingProcessCommand implements Command {

    private final RecruitingProcessService processService;
    private final static String ID = "id";
    private final static String RATING = "rating";
    private final static String STATE = "state";
    private final static String APPLICANTS = "applicants";
    private final static String PROCESS = "process";
    private final static String FEEDBACK = "feedback";

    public UpdateRecruitingProcessCommand(RecruitingProcessService processService) {
        this.processService = processService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServiceException {
        String idParam = request.getParameter(ID);
        Long id = Long.parseLong(idParam);
        String pointsParam = request.getParameter(RATING);
        Integer points = Integer.parseInt(pointsParam);
        String stateParam = request.getParameter(STATE);
        ApplicantState state = ApplicantState.fromString(stateParam);
        RecruitingProcess process = processService.getById(id);
        User user = process.getUser();
        Vacancy vacancy = process.getVacancy();
        RecruitingProcess newProcess = new RecruitingProcess(id, user, vacancy, state, points);
        if (state == process.getState() || state == ApplicantState.NEW) {
            processService.update(newProcess);
            return CommandResult.redirect(APPLICANTS);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(PROCESS, newProcess);
            return CommandResult.redirect(FEEDBACK);
        }
    }

}
