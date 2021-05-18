package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This interface represents that it's implementations will be used for mapping(creating) {@link Identifiable}
 * objects from {@link java.sql.ResultSet}
 *
 * @param <T> must be an implementation of {@link Identifiable} interface
 * @author Boris Prokhorenko
 * @see Identifiable
 * @see java.sql.ResultSet
 * @see com.epam.web.dao.Dao
 */
public interface RowMapper<T extends Identifiable> {

    /**
     * Method that maps {@link Identifiable} object from {@link java.sql.ResultSet}
     *
     * @param resultSet {@link java.sql.ResultSet}
     * @return {@link Identifiable} object
     * @throws SQLException cause is {@link java.sql.ResultSet}
     * @throws DaoException when impossible to map object
     */
    T map(ResultSet resultSet) throws SQLException, DaoException;

}
