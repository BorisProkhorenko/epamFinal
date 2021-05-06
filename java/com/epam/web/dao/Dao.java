package com.epam.web.dao;

import com.epam.web.entity.Identifiable;

import java.util.List;
import java.util.Optional;

/**
 * This Data Access Object(DAO) Interface represents the contract(CRUD), which all of DAO classes have to follow.
 * Uses {@link java.sql.PreparedStatement} to access Data Base.
 * Uses {@link com.epam.web.mapper.RowMapper} to map objects from {@link java.sql.ResultSet}
 * @param <T> must be an implementation of {@link Identifiable} interface
 * @see Identifiable
 * @see com.epam.web.mapper.RowMapper
 * @see java.sql.ResultSet
 * @see java.sql.PreparedStatement
 * @author Boris Prokhorenko
 */
public interface Dao<T extends Identifiable> {

    /**
     * Method allows to get {@link Identifiable} object from Data Base table by it's
     * @param id - primary key for all {@link Identifiable}
     * @return Optional of {@link Identifiable} object
     * @throws DaoException instead {@link java.sql.SQLException}
     */
    Optional<T> getById(Long id) throws DaoException;

    /**
     *  Method allows to get all from Data Base table
     * @return List of {@link Identifiable} objects from Data Base
     * @throws DaoException instead {@link java.sql.SQLException}
     */
    List<T> getAll() throws DaoException;

    /**
     * Method inserts new row in Data Base table if it doesn't exist and update if it do (check by item's id).
     * @param item implements ({@link Identifiable})
     * @throws DaoException instead {@link java.sql.SQLException}
     */
    void save(T item) throws DaoException;

    /**
     * Remove row from Data Base table by
     * @param id - primary key for all {@link Identifiable}
     * @throws DaoException instead {@link java.sql.SQLException}
     */
    void removeById(Long id) throws DaoException;


}
