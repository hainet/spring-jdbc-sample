package com.hainet.spring.jdbc.template.sample.domain;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    public Map<String, Object> findForMapById(final int id) {
        return this.jdbc.queryForMap("SELECT * FROM person WHERE id = ?", id);
    }

    public List<Person> findAll() {
        return this.jdbc.query(
                "SELECT * FROM person ORDER BY id",
                new BeanPropertyRowMapper<>(Person.class)
        );
    }
}
