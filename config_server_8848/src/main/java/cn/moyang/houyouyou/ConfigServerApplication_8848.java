package cn.moyang.houyouyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer //开启配置服务
public class ConfigServerApplication_8848 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication_8848.class);
    }
}
