package com.hainet.spring.jdbc.specify.initializing.datasource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class NamedParameterJdbcTemplateConfig {

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(
            final DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
