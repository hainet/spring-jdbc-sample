package com.hainet.spring.jdbc.template.sample.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
