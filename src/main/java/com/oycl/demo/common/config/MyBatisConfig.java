package com.oycl.demo.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.oycl.demo.orm")
public class MyBatisConfig {

//    @Bean
//    public SqlSessionFactory getSessionFactory(@Qualifier("dataSource")DataSource dataSource) throws Exception{
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
//
//        org.apache.ibatis.session.Configuration configuration  = new org.apache.ibatis.session.Configuration();
//        configuration.setMapUnderscoreToCamelCase(true);
//        sessionFactory.setConfiguration(configuration);
//        sessionFactory.setMapperLocations(resources);
//        sessionFactory.setDataSource(dataSource);
//
//        return sessionFactory.getObject();
//    }


}
