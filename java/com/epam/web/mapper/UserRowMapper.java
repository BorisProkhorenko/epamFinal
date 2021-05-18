package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.User;
import com.epam.web.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    public final static String ID = "id";
    public final static String USERNAME = "username";
    public final static String PASSWORD = "password";
    public final static String ROLE = "role";
    public final static String IS_BLOCKED = "is_blocked";


    @Override
    public User map(ResultSet resultSet) throws SQLException, DaoException {

        Long id = resultSet.getLong(ID);
        String username = resultSet.getString(USERNAME);
        String password = resultSet.getString(PASSWORD);
        boolean isBlocked = resultSet.getBoolean(IS_BLOCKED);
        String roleAsString = resultSet.getString(ROLE);
        Role role = Role.fromString(roleAsString);
        return createEntity(id, username, password, role, isBlocked, resultSet);

    }

    protected User createEntity(Long id, String username, String password, Role role, boolean isBlocked,
                                ResultSet resultSet) throws SQLException {

        if (role == Role.APPLICANT) {
            ApplicantRowMapper applicantRowMapper = new ApplicantRowMapper();
            return applicantRowMapper.createEntity(id, username, password, role, isBlocked, resultSet);
        } else {
            return new User(id, username, password, role, isBlocked);
        }
    }


}
