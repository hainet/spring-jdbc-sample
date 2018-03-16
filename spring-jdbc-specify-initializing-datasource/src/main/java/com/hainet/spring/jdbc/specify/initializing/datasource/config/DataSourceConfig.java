package com.hainet.spring.jdbc.specify.initializing.datasource.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource primaryDataSource(final DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean
    public DataSource secondaryDataSource(final DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }
}
