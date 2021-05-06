package com.epam.web.command;

import com.epam.web.entity.Vacancy;
import com.epam.web.service.ServiceException;
import com.epam.web.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetVacancyCommand implements Command {

    private final VacancyService service;
    private final static String ID = "id";
    private final static String VACANCY_ATTRIBUTE = "vacancy";
    private final static String EMPTY_PAGE = "empty";

    public GetVacancyCommand(VacancyService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        String idParam = request.getParameter(ID);
        Long id = Long.parseLong(idParam);
        Vacancy vacancy = service.getById(id);
        request.setAttribute(VACANCY_ATTRIBUTE, vacancy);
        return CommandResult.redirect(EMPTY_PAGE);
    }


}
