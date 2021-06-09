package top.putongren.dxcblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.putongren.dxcblog.dao")
public class ApplicationServerStart {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationServerStart.class,args);
    }
}
