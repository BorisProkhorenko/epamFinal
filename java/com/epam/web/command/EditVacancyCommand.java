package com.epam.web.command;

import com.epam.web.entity.Vacancy;
import com.epam.web.service.ServiceException;
import com.epam.web.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditVacancyCommand implements Command {

    private final VacancyService vacancyService;
    private static final String MAIN = "mainPage";
    private static final String ID = "vacancyId";
    private static final String NAME = "name";
    private static final String SALARY = "salary";
    private static final String DESCRIPTION = "description";
    private static final String RESPONSIBILITY = "responsibility";
    private static final String REQUIREMENTS = "requirements";

    public EditVacancyCommand(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession();
        String idParam = (String) session.getAttribute(ID);
        Long id = Long.parseLong(idParam);
        String name = request.getParameter(NAME);
        String salary = request.getParameter(SALARY);
        String description = request.getParameter(DESCRIPTION);
        String responsibility = request.getParameter(RESPONSIBILITY);
        String requirements = request.getParameter(REQUIREMENTS);
        Vacancy vacancy = new Vacancy(id, name, salary, responsibility, description, requirements);
        vacancyService.update(vacancy);
        return CommandResult.redirect(MAIN);
    }
}
