package com.epam.web.dao;


import com.epam.web.entity.Vacancy;
import com.epam.web.mapper.VacancyRowMapper;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;


public class VacancyDao extends AbstractDao<Vacancy> {


    private final static String INSERT_QUERY = "INSERT INTO VACANCY(name, salary, responsibility, description, " +
            "requirements) VALUES(?,?,?,?,?);";

    private final static String UPDATE_QUERY = "UPDATE VACANCY SET name=?, salary=?, responsibility=?, description=?, " +
            "requirements=? WHERE id=?;";

    public VacancyDao(Connection connection) {

        super(connection, new VacancyRowMapper());
    }

    @Override
    protected List<Object> extractParams(Vacancy item) {
        return Arrays.asList(
                item.getName(),
                item.getSalary(),
                item.getResponsibility(),
                item.getDescription(),
                item.getRequirements());
    }

    @Override
    protected String getTableName() {
        return Vacancy.TABLE_NAME;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }
}
