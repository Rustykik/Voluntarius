package com.voluntarius.config;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
@PropertySource("application.properties")
public class VoluntariousApplicationConfig {
    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("{spring.datasource.user}")
    private String DB_USER;
    @Value("{spring.datasource.password}")
    private String DB_PASSWORD;

    @Bean
    @Primary
    public DataSource getDataSource() {
        return JdbcConnectionPool.create(DB_URL, DB_USER, DB_PASSWORD);
    }

    @Bean
    @Primary
    public JdbcTemplate source(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
