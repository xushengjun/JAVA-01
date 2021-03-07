package com.gane.maple.jdbc.datasource;

import com.gane.maple.jdbc.routing.ClientDataSource;
import com.gane.maple.jdbc.routing.component.ClientDataSourceRouter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author maple
 * @date 2021/3/3
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slaveDataSource2")
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    public DataSource slaveDataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "dynamicDatasource")
    public ClientDataSourceRouter dynamicDatasource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                                    @Qualifier("slaveDataSource") DataSource slaveDataSource,
                                                    @Qualifier("slaveDataSource2") DataSource slaveDataSource2) {

        ClientDataSourceRouter dataSourceRouter = new ClientDataSourceRouter();
        dataSourceRouter.setDefaultTargetDataSource(masterDataSource);

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(ClientDataSource.MASTER, masterDataSource);
        targetDataSources.put(ClientDataSource.SLAVE, slaveDataSource);
        targetDataSources.put(ClientDataSource.SLAVE2, slaveDataSource2);
        dataSourceRouter.setTargetDataSources(targetDataSources);

        return dataSourceRouter;
    }
}























