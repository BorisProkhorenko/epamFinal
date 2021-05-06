package com.epam.web.dao;

import com.epam.web.entity.Applicant;
import com.epam.web.entity.User;
import com.epam.web.enums.Gender;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public class ApplicantDao extends UserDao {

    private final static String UPDATE_QUERY = "UPDATE USER SET name=?, gender=?, age=?, contacts=?, " +
            "education=?, experience=?, skills=? WHERE id=?";

    private final static String UPDATE_PHOTO = "UPDATE USER SET photo=? WHERE id=?";


    public ApplicantDao(Connection connection) {
        super(connection);
    }


    @Override
    protected List<Object> extractParams(User item) {
        Applicant applicant = (Applicant) item;
        Gender gender = applicant.getGender();
        return Arrays.asList(
                applicant.getName(),
                gender.toString(),
                applicant.getAge(),
                applicant.getContacts(),
                applicant.getEducation(),
                applicant.getExperience(),
                applicant.getSkills());
    }

    public void updatePhoto(String photo, Long id) throws DaoException {
        executeUpdate(UPDATE_PHOTO, photo, id);
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }
}
