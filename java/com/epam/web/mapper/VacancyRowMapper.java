package com.epam.web.mapper;

import com.epam.web.entity.Vacancy;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VacancyRowMapper implements RowMapper<Vacancy> {

    public final static String ID = "id";
    public final static String NAME = "name";
    public final static String SALARY = "salary";
    public final static String RESPONSIBILITY = "responsibility";
    public final static String DESCRIPTION = "description";
    public final static String REQUIREMENTS = "requirements";


    @Override
    public Vacancy map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        String salary = resultSet.getString(SALARY);
        String responsibility = resultSet.getString(RESPONSIBILITY);
        String description = resultSet.getString(DESCRIPTION);
        String requirements = resultSet.getString(REQUIREMENTS);

        return new Vacancy(id, name, salary, responsibility, description, requirements);

    }
}
