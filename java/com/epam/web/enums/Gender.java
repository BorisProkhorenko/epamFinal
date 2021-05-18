package com.epam.web.enums;


import java.util.Arrays;
import java.util.Optional;

public enum Gender {

    MALE,
    FEMALE;


    public static Gender fromString(String text) {
        Optional<Gender> optionalGender = Arrays.stream(Gender.values())
                .filter(gender -> gender.toString().equalsIgnoreCase(text))
                .findAny();
        if (optionalGender.isPresent()) {
            return optionalGender.get();
        } else {
            throw new IllegalArgumentException("Unknown gender");
        }
    }
}
