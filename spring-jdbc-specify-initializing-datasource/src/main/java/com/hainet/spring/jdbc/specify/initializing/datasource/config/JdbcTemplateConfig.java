package com.hainet.spring.jdbc.specify.initializing.datasource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {

    @Bean
    @Primary
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("primaryDataSource") final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("secondaryDataSource") final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
