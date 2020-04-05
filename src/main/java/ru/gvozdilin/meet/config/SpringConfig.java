package ru.gvozdilin.meet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration


@ComponentScan(basePackages = "ru.gvozdilin.meet")
//иищет аннотированные классы, чтобы зарегистрировать их как бины в спринговском контексте.


public class SpringConfig {
    @Bean
    JdbcTemplate  getJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }
    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/meeting?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }
}
