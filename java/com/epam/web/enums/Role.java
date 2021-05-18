package com.epam.web.enums;


import java.util.Arrays;
import java.util.Optional;

public enum Role {
    ADMIN,
    HR,
    APPLICANT;

    public static Role fromString(String text) {
        Optional<Role> optionalGender = Arrays.stream(Role.values())
                .filter(role -> role.toString().equalsIgnoreCase(text))
                .findAny();
        if (optionalGender.isPresent()) {
            return optionalGender.get();
        } else {
            throw new IllegalArgumentException("Unknown role");
        }

    }
}
