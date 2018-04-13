package com.hainet.spring.jdbc.specify.initializing.datasource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NamedParameterJdbcTemplateTest {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Test
    public void namedParameterJdbcTemplateTest_withMap() {
        // Setup
        final Map<String, Object> expected = new HashMap<>();
        expected.put("ID", 1);
        expected.put("NAME", "hainet");

        // Exercise
        final Map<String, Object> actual = jdbcTemplate.queryForMap(
                "SELECT * FROM person WHERE id = :ID AND name = :NAME",
                expected
        );

        // Verify
        assertThat(actual, is(expected));
    }

    @Test
    public void namedParameterJdbcTemplateTest_withSqlParameterSource() {
        // Setup
        final Map<String, Object> expected = new HashMap<>();
        expected.put("ID", 1);
        expected.put("NAME", "hainet");

        final MapSqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("ID", 1)
                .addValue("NAME", "hainet");

        // Exercise
        final Map<String, Object> actual = jdbcTemplate.queryForMap(
                "SELECT * FROM person WHERE id = :ID AND name = :NAME",
                paramSource
        );

        // Verify
        assertThat(actual, is(expected));
    }
}
