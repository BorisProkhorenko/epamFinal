package com.epam.web.dao;

import com.epam.web.connection.ConnectionPool;


public class DaoHelperFactory {

    public DaoHelper create() {
        ConnectionPool pool = ConnectionPool.getInstance();
        return new DaoHelper(pool.getConnection());
    }
}

