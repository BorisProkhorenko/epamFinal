package com.epam.web.validator;

import com.epam.web.entity.Response;


public class ResponseValidator implements Validator<Response> {

    private final static int MAX_SUBJECT = 1800;
    private final static int MAX_DETAILS = 7000;

    @Override
    public boolean validate(Response item) {

        String subject = item.getSubject();
        int subjectLength = subject.length();
        String details = item.getDetails();
        int detailsLength = details.length();
        return subjectLength <= MAX_SUBJECT && detailsLength <= MAX_DETAILS;

    }
}
