package com.epam.web.connection;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionsInUse;

    private final static int POOL_SIZE = 10;
    private final static AtomicReference<ConnectionPool> INSTANCE = new AtomicReference<>();
    private final static Lock INSTANCE_LOCK = new ReentrantLock();

    private final Lock connectionsLock = new ReentrantLock();
    private final ConnectionFactory connectionFactory;
    private final Semaphore connectionsSemaphore;

    private ConnectionPool() throws ConnectionPoolException {
        availableConnections = new ArrayDeque<>();
        connectionsInUse = new ArrayDeque<>();
        connectionsSemaphore = new Semaphore(POOL_SIZE);
        connectionFactory = new ConnectionFactory();
        createConnections();
    }

    public static ConnectionPool getInstance() {
        if (INSTANCE.get() == null) {
            INSTANCE_LOCK.lock();

            try {
                if (INSTANCE.get() == null) {
                    ConnectionPool pool = new ConnectionPool();
                    INSTANCE.getAndSet(pool);
                }

            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }

    private void createConnections() {

        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = connectionFactory.create();
                ProxyConnection proxyConnection = new ProxyConnection(connection, this);
                availableConnections.add(proxyConnection);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }


    public void returnConnection(ProxyConnection connection) {
        connectionsLock.lock();
        try {
            if (connectionsInUse.contains(connection)) {
                connectionsInUse.remove(connection);
                availableConnections.add(connection);

                connectionsSemaphore.release();
            }
        } finally {
            connectionsLock.unlock();
        }
    }

    public ProxyConnection getConnection() {
        try {
            connectionsSemaphore.acquire();
            connectionsLock.lock();

            ProxyConnection connection = availableConnections.poll();
            connectionsInUse.add(connection);
            return connection;

        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e.getMessage(), e);

        } finally {
            connectionsLock.unlock();
        }
    }
}
