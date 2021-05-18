package com.epam.web.command;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.User;
import com.epam.web.entity.Vacancy;
import com.epam.web.enums.ApplicantState;
import com.epam.web.service.RecruitingProcessService;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ApplyCommand implements Command {

    private final RecruitingProcessService processService;
    private final UserService userService;
    private final VacancyService vacancyService;
    private final static String MAIN = "mainPage";
    private final static String USER_ID = "id";
    private final static String VACANCY_ID = "vacancyId";
    private final static ApplicantState DEFAULT_STATE = ApplicantState.NEW;
    private final static Integer DEFAULT_POINTS = 0;
    private final static Long MOCK_ID = 0L;

    public ApplyCommand(RecruitingProcessService processService, UserService userService, VacancyService vacancyService) {
        this.processService = processService;
        this.userService = userService;
        this.vacancyService = vacancyService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(USER_ID);
        String vacancyIdParam = (String) session.getAttribute(VACANCY_ID);
        Long vacancyId = Long.parseLong(vacancyIdParam);
        if (processService.isEmpty(userId, vacancyId)) {
            User user = userService.getById(userId);
            Vacancy vacancy = vacancyService.getById(vacancyId);
            RecruitingProcess process = new RecruitingProcess(MOCK_ID, user, vacancy, DEFAULT_STATE, DEFAULT_POINTS);
            processService.update(process);
        }
        return CommandResult.redirect(MAIN);
    }
}
