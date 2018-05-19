package com.hainet.spring.jdbc.template.sample.domain;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao {

    private final JdbcTemplate jdbc;

    public PersonDao(final JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Person findById(final int id) {
        return this.jdbc.queryForObject(
                "SELECT * FROM person WHERE id = ?",
                new BeanPropertyRowMapper<>(Person.class),
                id
        );
    }
}
