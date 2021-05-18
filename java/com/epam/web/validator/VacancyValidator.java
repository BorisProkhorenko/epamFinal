package com.epam.web.validator;

import com.epam.web.entity.Vacancy;


public class VacancyValidator implements Validator<Vacancy> {

    private final static String NAME_SALARY_PATTERN = ".{1,350}";
    private final static int MAX = 7000;

    @Override
    public boolean validate(Vacancy item) {
        String name = item.getName();
        String salary = item.getSalary();
        String responsibility = item.getResponsibility();
        int responsibilityLength = responsibility.length();
        String description = item.getDescription();
        int descriptionLength = description.length();
        String requirements = item.getRequirements();
        int requirementsLength = requirements.length();

        return name.matches(NAME_SALARY_PATTERN) && salary.matches(NAME_SALARY_PATTERN)
                && requirementsLength <= MAX && responsibilityLength <= MAX && descriptionLength <= MAX;

    }
}
