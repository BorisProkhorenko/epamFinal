package com.epam.web.command;

import com.epam.web.entity.Vacancy;
import com.epam.web.service.ServiceException;
import com.epam.web.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetVacanciesByPageCommand implements Command {

    private final VacancyService service;
    private final static String PAGE_INDEX = "pageIndex";
    private final static String VACANCY_LIST = "vacancyList";
    private final static String VACANCIES_ON_PAGE = "VacanciesOnPage";
    private final static String VACANCIES_COUNT = "VacanciesCount";
    private final static String MAIN = "mainPage";

    public GetVacanciesByPageCommand(VacancyService service) {
        this.service = service;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(PAGE_INDEX);
        int pageIndex = Integer.parseInt(page);
        List<Vacancy> vacancyList = service.getVacanciesByPage(pageIndex);
        request.setAttribute(VACANCY_LIST, vacancyList);
        request.setAttribute(VACANCIES_ON_PAGE, service.getVacanciesOnPage());
        List<Vacancy> all = service.getAll();
        request.setAttribute(VACANCIES_COUNT, all.size());
        return CommandResult.redirect(MAIN);
    }
}
