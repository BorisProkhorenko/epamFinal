package com.epam.web.validator;

import com.epam.web.entity.User;


public class LoginValidator implements Validator<User> {

    private final static String PATTERN = "[a-zA-Z0-9]{1,255}";

    @Override
    public boolean validate(User item) {
        String username = item.getUsername();
        String password = item.getPassword();
        return username.matches(PATTERN) && password.matches(PATTERN);
    }

}
