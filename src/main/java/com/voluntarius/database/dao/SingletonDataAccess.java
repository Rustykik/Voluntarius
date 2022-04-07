package com.voluntarius.database.dao;

import com.voluntarius.database.db.SimpleJdbcTemplate;
import org.h2.jdbcx.JdbcConnectionPool;

// change lazy Singleton to thread-safety
public class SingletonDataAccess {
    private static SingletonDataAccess instance;
    private static SimpleJdbcTemplate source; // maybe we can get source without instance of class

    private SingletonDataAccess () {
        this.source = new SimpleJdbcTemplate(JdbcConnectionPool.create("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=runscript from 'src/main/resources/schema.sql'", "", ""));
    }
    public static SingletonDataAccess getInstance() {
        if (instance == null) {
            System.out.println("making new instance");
            instance = new SingletonDataAccess();
        }
        return instance;
    }
    public SimpleJdbcTemplate getSource() {
        return this.source;
    }
}
