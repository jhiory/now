package doit.now.config;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

//@Configuration
//@MapperScan(basePackages  = {"doit.now.mapper","doit.now.security.repository"})
//public class MybatisConfig {
//
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setTypeAliasesPackage("doit.now.vo");
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/mappers/*.xml"));
//        return sessionFactory;
//    }
//}
@Configuration
@MapperScan( basePackages =  {"doit.now.mapper","doit.now.security.repository"}, sqlSessionFactoryRef = "sqlSessionFactory" )

public class MybatisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception
    {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
    factory.setDataSource(dataSource);
    factory.setMapperLocations(applicationContext.getResources("classpath*:mybatis/mappers/*.xml"));
    factory.setTypeAliasesPackage("doit.now.vo");
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
