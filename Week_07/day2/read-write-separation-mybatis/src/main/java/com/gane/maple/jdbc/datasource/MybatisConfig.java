package com.gane.maple.jdbc.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author maple
 * @date 2021/3/3
 */
@Configuration
@MapperScan(MybatisConfig.MAPPER_PACKAGE)
public class MybatisConfig {

    public static final String MAPPER_PACKAGE = "com.gane.maple.dao";
    public static final String TYPE_ALIASES_PACKAGE = "com.gane.maple.dao.entity";
    public static final String MAPPER_XML_LOCATIONS = "mapper/*Mapper.xml";

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_LOCATIONS));
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager( DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
