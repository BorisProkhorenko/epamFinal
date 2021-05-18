package com.epam.web.enums;


import java.util.Arrays;
import java.util.Optional;

public enum ApplicantState {

    NEW,
    PRELIMINARY,
    TECHNICAL,
    HIRED,
    REJECTED;


    public static ApplicantState fromString(String text) {
        Optional<ApplicantState> optionalState = Arrays.stream(ApplicantState.values())
                .filter(state -> state.toString().equalsIgnoreCase(text))
                .findAny();
        if (optionalState.isPresent()) {
            return optionalState.get();
        } else {
            throw new IllegalArgumentException("Unknown state");
        }
    }
}
