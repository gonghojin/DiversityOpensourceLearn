package com.gongdel.spring.configuration;


import javax.sql.DataSource;

public interface DatabaseConfig {

    DataSource createDataSource();
}
