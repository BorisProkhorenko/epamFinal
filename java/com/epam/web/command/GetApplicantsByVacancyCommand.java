package com.epam.web.command;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.Vacancy;
import com.epam.web.service.RecruitingProcessService;
import com.epam.web.service.ServiceException;
import com.epam.web.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetApplicantsByVacancyCommand implements Command {
    private final RecruitingProcessService processService;
    private final VacancyService vacancyService;
    private final static String ID = "vacancyId";
    private final static String APPLICANT_LIST_ATTRIBUTE = "applicantList";
    private final static String APPLICANTS = "applicants";
    private final static String VACANCY = "vacancy";

    public GetApplicantsByVacancyCommand(RecruitingProcessService processService, VacancyService vacancyService) {
        this.processService = processService;
        this.vacancyService = vacancyService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession();
        String idParam = (String) session.getAttribute(ID);
        Long id = Long.parseLong(idParam);
        List<RecruitingProcess> applicantList = processService.getByVacancyId(id);
        Vacancy vacancy = vacancyService.getById(id);
        request.setAttribute(VACANCY, vacancy);
        request.setAttribute(APPLICANT_LIST_ATTRIBUTE, applicantList);

        return CommandResult.redirect(APPLICANTS);
    }
}
