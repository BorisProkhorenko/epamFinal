package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.*;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private final ProxyConnection connection;

    public DaoHelper(ProxyConnection connection) {
        this.connection = connection;
    }

    public AbstractDao createDao(String daoType) throws DaoException {
        switch (daoType) {
            case User.TABLE_NAME:
                return new UserDao(connection);
            case Applicant.APPLICANT:
                return new ApplicantDao(connection);
            case Vacancy.TABLE_NAME:
                return new VacancyDao(connection);
            case Response.TABLE_NAME:
                return new ResponseDao(connection);
            case RecruitingProcess.TABLE_NAME:
                return new RecruitingProcessDao(connection);
            default:
                throw new DaoException("Unknown DAO type");
        }
    }

    @Override
    public void close() {
        connection.close();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
