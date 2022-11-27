package ru.javaops.bootjava.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.javaops.bootjava.util.JsonUtil;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

@Configuration
@Slf4j
@AllArgsConstructor
public class AppConfig {

    private final ObjectMapper objectMapper;

    @PostConstruct
    void setMapper() {
        JsonUtil.setObjectMapper(objectMapper);
    }

/*
    Веб консоль можно так поднять или как у нас в проперти
    сделано spring.database.h2.console.enabled=true
    http://localhost:8082/h2-console
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2WebServer() throws SQLException {
        return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
    }
*/

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        log.info("Start H2 TCP server for connect to memory database : jdbc:h2:tcp://localhost:9092/mem:voting");
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}
