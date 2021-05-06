package com.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CreateVacancyCommand implements Command {

    private static final String VACANCY_ID = "vacancyId";
    private static final String EDIT = "editVacancy";
    private static final String NULL_ID = "0";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(VACANCY_ID, NULL_ID);
        return CommandResult.redirect(EDIT);
    }
}
