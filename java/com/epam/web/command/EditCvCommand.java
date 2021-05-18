package com.epam.web.command;

import com.epam.web.entity.Applicant;
import com.epam.web.entity.User;
import com.epam.web.enums.Gender;
import com.epam.web.service.ApplicantService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditCvCommand implements Command {

    private final ApplicantService applicantService;
    private static final String CV = "cv";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String GENDER = "gender";
    private static final String AGE = "age";
    private static final String EMPTY = "";
    private static final String CONTACTS = "contacts";
    private static final String EDUCATION = "education";
    private static final String EXPERIENCE = "experience";
    private static final String SKILLS = "skills";

    public EditCvCommand(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute(ID);
        String name = request.getParameter(NAME);
        String ageParam = request.getParameter(AGE);
        Integer age = Integer.parseInt(ageParam);
        String contacts = request.getParameter(CONTACTS);
        String education = request.getParameter(EDUCATION);
        String experience = request.getParameter(EXPERIENCE);
        String skills = request.getParameter(SKILLS);
        Gender gender = Gender.fromString(request.getParameter(GENDER));
        User user = applicantService.getById(id);
        Applicant applicant = new Applicant(user, name, gender, age, EMPTY, contacts, education, experience, skills);
        applicantService.update(applicant);

        return CommandResult.redirect(CV);
    }


}
