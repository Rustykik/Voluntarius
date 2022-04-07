package com.voluntarius.database.db;

import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

@AllArgsConstructor
public class SimpleJdbcTemplate {
    @FunctionalInterface
    public interface SQLFunction<R, T> {
        R apply(T object) throws SQLException;
    }
    @FunctionalInterface
    public interface SQLConsumer<T> {
        void accept(T object) throws SQLException;
    }
    private final DataSource connectionPool;

    public void connection(SQLConsumer<? super Connection> consumer) throws  SQLException {
        Objects.requireNonNull(consumer);
        try (Connection conn = connectionPool.getConnection()) {
            consumer.accept(conn);
        };
    }
    public <R> R connection(SQLFunction<? extends R, ? super Connection> function) throws SQLException {
        Objects.requireNonNull(function);
        try (Connection conn = connectionPool.getConnection()) {
            return function.apply(conn);
        }
    }

    public void statement(SQLConsumer<? super Statement> consumer) throws SQLException {
        Objects.requireNonNull(consumer);
        connection( conn -> {
           try (Statement stmt = conn.createStatement()) {
               consumer.accept(stmt);
           }
        });
    }

    public <R> R statement(SQLFunction<? extends R, ? super Statement> function) throws SQLException {
        Objects.requireNonNull(function);
        return connection( conn -> {
            try (Statement stmt = conn.createStatement()) {
                return function.apply(stmt);
            }
        });
    }

    public void prepareStatement(String sqlStatement,
                                 SQLConsumer<? super PreparedStatement> consumer) throws SQLException {
        Objects.requireNonNull(consumer);
        connection( conn -> {
            try (PreparedStatement stmt = conn.prepareStatement(sqlStatement)) {
                consumer.accept(stmt);
            }
        });
    }

    public <R> R prepareStatement(String sqlStatement,
                                  SQLFunction<? extends R, ? super PreparedStatement> function) throws SQLException {
        Objects.requireNonNull(function);
        return connection( conn -> {
            try (PreparedStatement stmt = conn.prepareStatement(sqlStatement)) {
               return function.apply(stmt);
            }
        });
    }

}
