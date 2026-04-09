package com.bridgelabz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static final int MAX_POOL_SIZE = 5;
    private static final List<Connection> pool = new ArrayList<>();

    static {
        try {
            String url = AppConfig.get("db.url");
            String user = AppConfig.get("db.user");
            String pass = AppConfig.get("db.password");

            for (int i = 0; i < MAX_POOL_SIZE; i++) {
                pool.add(DriverManager.getConnection(url, user, pass));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error initializing pool", e);
        }
    }

    public static synchronized Connection getConnection() {
        if (pool.isEmpty()) {
            throw new RuntimeException("No available connections");
        }
        return pool.remove(pool.size() - 1);
    }

    public static synchronized void releaseConnection(Connection conn) {
        pool.add(conn);
    }
}