import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean; import org.mybatis.spring.annotation.MapperScan; import org.springframework.context.ApplicationContext; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; @Configuration @MapperScan( basePackages = "com.tistory.aljjabaegi.api.mapper", sqlSessionFactoryRef = "sqlSessionFactory" ) public class MybatisConfig { @Bean public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception { SqlSessionFactoryBean factory = new SqlSessionFactoryBean(); factory.setDataSource(dataSource); factory.setMapperLocations(applicationContext.getResources("classpath*:com/tistory/aljjabaegi/api/mapper/*.xml")); factory.setTypeAliasesPackage("com.tistory.aljjabaegi.api.domain");
    return getSqlSessionFactory(factory);
} }

