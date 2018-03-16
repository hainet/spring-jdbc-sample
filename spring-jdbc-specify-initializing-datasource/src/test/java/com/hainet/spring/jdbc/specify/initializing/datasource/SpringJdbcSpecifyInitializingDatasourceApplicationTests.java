package com.hainet.spring.jdbc.specify.initializing.datasource;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringJdbcSpecifyInitializingDatasourceApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primary;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondary;

    @Test
    public void primaryToPrimaryTest() {
        // Exercise
        final String actual = primary.queryForMap("SELECT * FROM `primary`").get("name").toString();

        // Verify
        assertThat(actual, is(CoreMatchers.containsString("primary")));
    }

    @Test(expected = BadSqlGrammarException.class)
    public void primaryToSecondaryTest() {
        // Exercise & Verify
        primary.queryForMap("SELECT * FROM `secondary`");
    }

    @Test
    public void secondaryToSecondaryTest() {
        // Exercise
        final String actual = secondary.queryForMap("SELECT * FROM `secondary`").get("name").toString();

        // Verify
        assertThat(actual, is(CoreMatchers.containsString("secondary")));
    }

    @Test(expected = BadSqlGrammarException.class)
    public void secondaryToPrimaryTest() {
        // Exercise & Verify
        secondary.queryForMap("SELECT * FROM `primary`");
    }
}
