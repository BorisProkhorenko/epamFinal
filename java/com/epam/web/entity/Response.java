package com.epam.web.entity;

import java.util.Date;

public class Response implements Identifiable {

    public final static String TABLE_NAME = "response";

    private final Long id;
    private final String subject;
    private final String details;
    private final Date date;
    private final RecruitingProcess recruitingProcess;

    public Response(Long id, String subject, String details, Date date, RecruitingProcess recruitingProcess) {
        this.id = id;
        this.subject = subject;
        this.details = details;
        this.date = date;
        this.recruitingProcess = recruitingProcess;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getDetails() {
        return details;
    }

    public Date getDate() {
        return date;
    }

    public RecruitingProcess getRecruitingProcess() {
        return recruitingProcess;
    }
}
