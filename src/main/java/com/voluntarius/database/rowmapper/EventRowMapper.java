package com.voluntarius.database.rowmapper;

import com.voluntarius.models.Event;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventRowMapper implements RowMapper<Event> {
    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Event(rs.getInt("id"),
                        rs.getString("eventName"),
                        rs.getString("description"),
                        rs.getTimestamp("eventStart").toLocalDateTime(),
                        rs.getTimestamp("eventEnd").toLocalDateTime(),
                        rs.getString("location"),
                        rs.getInt("owner_id"));
    }
}
