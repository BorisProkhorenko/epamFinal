package com.epam.web.entity;

import com.epam.web.enums.ApplicantState;

public class RecruitingProcess implements Identifiable {

    public final static String TABLE_NAME = "user_vacancy";
    private final Long id;
    private final User user;
    private final Vacancy vacancy;
    private final ApplicantState state;
    private final Integer rating;

    public RecruitingProcess(Long id, User user, Vacancy vacancy, ApplicantState state, Integer rating) {
        this.id = id;
        this.user = user;
        this.vacancy = vacancy;
        this.state = state;
        this.rating = rating;
    }


    @Override
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public ApplicantState getState() {
        return state;
    }

    public Integer getRating() {
        return rating;
    }

}
