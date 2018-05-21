package com.hainet.spring.jdbc.template.sample.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonDaoTest {

    @Autowired
    private PersonDao dao;

    @Test
    public void findByIdTest() {
        // Setup
        final Person person = new Person();
        person.setId(1);
        person.setName("hainet");

        // Exercise and Verify
        assertThat(this.dao.findById(1), is(person));
    }

    @Test
    public void findForMapByIdTest() {
        // Setup
        final Map<String, Object> map = new HashMap<>();
        map.put("ID", 1);
        map.put("NAME", "hainet");

        // Exercise and Verify
        assertThat(this.dao.findForMapById(1), is(map));
    }

    @Test
    public void findAllTest() {
        // Setup
        final Person hainet = new Person();
        hainet.setId(1);
        hainet.setName("hainet");
        final Person spring = new Person();
        spring.setId(2);
        spring.setName("spring");
        final Person jdbc = new Person();
        jdbc.setId(3);
        jdbc.setName("jdbc");

        // Exercise and Verify
        assertThat(this.dao.findAll(), is(Arrays.asList(hainet, spring, jdbc)));
    }
}
