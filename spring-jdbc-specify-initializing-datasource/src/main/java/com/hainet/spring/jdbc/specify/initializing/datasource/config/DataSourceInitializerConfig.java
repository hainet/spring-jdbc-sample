package com.hainet.spring.jdbc.specify.initializing.datasource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DataSourceInitializerConfig {

    private final ApplicationContext context;

    public DataSourceInitializerConfig(final ApplicationContext context) {
        this.context = context;
    }

    @Bean
    @Primary
    public DataSourceInitializer primaryDataSourceInitializer(
            @Qualifier("primaryDataSource") final DataSource dataSource,
            @Qualifier("primaryDataSourceProperties") final DataSourceProperties properties) {
        return createDataSourceInitializer(dataSource, properties);
    }

    @Bean
    public DataSourceInitializer secondaryDataSourceInitializer(
            @Qualifier("secondaryDataSource") final DataSource dataSource,
            @Qualifier("secondaryDataSourceProperties") final DataSourceProperties properties) {
        return createDataSourceInitializer(dataSource, properties);
    }

    private DataSourceInitializer createDataSourceInitializer(
            final DataSource dataSource,
            final DataSourceProperties properties) {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        properties.getSchema().stream()
                .map(context::getResource)
                .forEach(populator::addScript);
        properties.getData().stream()
                .map(context::getResource)
                .forEach(populator::addScript);

        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(populator);
        initializer.setEnabled(true);

        return initializer;
    }
}
