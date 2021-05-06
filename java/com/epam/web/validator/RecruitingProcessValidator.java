package com.epam.web.validator;

import com.epam.web.entity.RecruitingProcess;

public class RecruitingProcessValidator implements Validator<RecruitingProcess> {

    private final static int MIN_RATING = 0;
    private final static int MAX_RATING = 100;

    @Override
    public boolean validate(RecruitingProcess item) {
        int rating = item.getRating();
        return rating >= MIN_RATING && rating <= MAX_RATING;
    }
}
