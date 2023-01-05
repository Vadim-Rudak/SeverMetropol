package com.bcg.SeverMetropol.config;

import com.bcg.SeverMetropol.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.bcg.SeverMetropol")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Autowired
    Environment environment;

    private final String URL = "url";
    private final String USER = "dbuser";
//    private final String DRIVER = "driver";
    private final String PASSWORD = "dbpassword";

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }


    @Bean
    DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(environment.getProperty(URL));
        driverManagerDataSource.setUsername(environment.getProperty(USER));
        driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
//        driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
        return driverManagerDataSource;
    }

    @Bean
    public UserRepo getUserDao(){
        return new UserImpl(getJdbcTemplate());
    }

    @Bean
    public PhotoRepo getPhotoDao(){
        return new PhotoImpl(getJdbcTemplate());
    }

    @Bean
    public MoreUserInfoRepo getMoreUserInfoDao(){
        return new MoreUserInfoImpl(getJdbcTemplate());
    }

    @Bean
    public NewsRepo getNewsDao(){
        return new NewsImpl(getJdbcTemplate());
    }

    @Bean
    public NewsImgRepo getNewsImgDao(){
        return new NewsImgImpl(getJdbcTemplate());
    }

    @Bean
    public TaskRepo getTaskDao(){
        return new TaskImpl(getJdbcTemplate());
    }

    @Bean
    public DocumentRepo getDocumentDao(){
        return new DocumentImpl(getJdbcTemplate());
    }
//
//    @Bean
//    public TaskRepo getTaskDao(){
//        return new TaskImpl(getJdbcTemplate());
//    }
//
//    @Bean
//    public FileRepo getFileDao(){
//        return new FileImpl(getJdbcTemplate());
//    }

//    @Bean
//    public EventsImageRepo getEventsImageDao(){
//        return new EventsImageImpl(getJdbcTemplate());
//    }
//
//    @Bean
//    public EventsRepo getEventsDao(){
//        return new EventsImpl(getJdbcTemplate());
//    }
//
//    @Bean
//    public MoreUserInfoRepo getMoreUserInfoDao(){
//        return new MoreUserInfoImpl(getJdbcTemplate());
//    }
//
//    @Bean
//    public PhotoRepo getPhotoDao(){
//        return new PhotoImpl(getJdbcTemplate());
//    }
//
//
//    @Bean
//    public RegRepo getRegDao(){
//        return new RegImpl(getJdbcTemplate());
//    }
//
//    @Bean
//    public UseEventsRepo getUserEventsDao(){
//        return new UseEventsImpl(getJdbcTemplate());
//    }
//
//    @Bean
//    public UserRepo getUserDao(){
//        return new UserImpl(getJdbcTemplate());
//    }
//
//    @Bean
//    public DialogRepo getDialogDao(){
//        return new DialogImpl(getJdbcTemplate());
//    }

}
