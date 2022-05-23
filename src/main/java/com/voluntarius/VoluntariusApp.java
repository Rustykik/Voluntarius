package com.voluntarius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
class VoluntariousApplication {
    //CHANGE .sql to file
    public static void main(String[] args) throws IOException, SQLException {
        SpringApplication.run(VoluntariousApplication.class, args);
    }
}
