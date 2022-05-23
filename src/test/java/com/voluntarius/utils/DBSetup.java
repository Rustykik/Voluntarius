package com.voluntarius.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class DBSetup {
    private final JdbcTemplate source;

    private String getSQL(String name) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        DBSetup.class.getResourceAsStream(name),
                        StandardCharsets.UTF_8))) {
            return br.lines().collect(Collectors.joining("\n"));
        }
    }

    public void create() throws IOException {
        String sql = getSQL("/schema.sql");
        source.execute(sql);
    }

    public void fill() throws IOException {
        String sql = getSQL("/data.sql");
        source.execute(sql);
    }

    public void drop() {
        source.execute("drop all objects;");
    }
}
