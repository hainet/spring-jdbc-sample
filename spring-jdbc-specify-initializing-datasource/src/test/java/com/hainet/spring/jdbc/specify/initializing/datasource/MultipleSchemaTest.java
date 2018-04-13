package com.hainet.spring.jdbc.specify.initializing.datasource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultipleSchemaTest {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primary;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondary;

    @Test
    public void primaryToPrimaryTest() {
        // Setup
        final Map<String, Object> expected = new HashMap<>();
        expected.put("ID", 1);
        expected.put("NAME", "primary");

        // Exercise
        final Map<String, Object> actual = primary.queryForMap("SELECT * FROM `primary`");

        // Verify
        assertThat(actual, is(expected));
    }

    @Test(expected = BadSqlGrammarException.class)
    public void primaryToSecondaryTest() {
        // Exercise & Verify
        primary.queryForMap("SELECT * FROM `secondary`");
    }

    @Test
    public void secondaryToSecondaryTest() {
        // Setup
        final Map<String, Object> expected = new HashMap<>();
        expected.put("ID", 1);
        expected.put("NAME", "secondary");

        // Exercise
        final Map<String, Object> actual = secondary.queryForMap("SELECT * FROM `secondary`");

        // Verify
        assertThat(actual, is(expected));
    }

    @Test(expected = BadSqlGrammarException.class)
    public void secondaryToPrimaryTest() {
        // Exercise & Verify
        secondary.queryForMap("SELECT * FROM `primary`");
    }
}
