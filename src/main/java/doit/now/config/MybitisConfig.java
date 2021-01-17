package com.example.vi.config;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan( basePackages =  {"com.example.vi.mapper","com.example.vi.security.repository"}, sqlSessionFactoryRef = "sqlSessionFactory" )

public class MybitisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception
    {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
    factory.setDataSource(dataSource);
    factory.setMapperLocations(applicationContext.getResources("classpath*:myba/mappers/*.xml"));
    factory.setTypeAliasesPackage("com.example.vi.domain");
        return getSqlSessionFactory(factory);
    }

    public static SqlSessionFactory getSqlSessionFactory(SqlSessionFactoryBean factory) throws Exception {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setJdbcTypeForNull(null);
        configuration.setCacheEnabled(false);
        configuration.setMapUnderscoreToCamelCase(true);
        factory.setConfiguration(configuration);
        return factory.getObject();
    }

}
