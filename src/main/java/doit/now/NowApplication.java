package doit.now;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages  = {"doit.now.mapper","doit.now.security.repository"})  // 경로 주의

public class NowApplication {

    public static void main(String[] args) {
        SpringApplication.run(NowApplication.class, args);
    }

}
