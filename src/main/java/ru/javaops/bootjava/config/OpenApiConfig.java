package ru.javaops.bootjava.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@OpenAPIDefinition(
        info = @Info(
                title = "REST API documentation",
                version = "1.0.0",
                description = """
                        Приложение по <a href='https://javaops.ru/view/bootjava'>курсу BootJava</a>
                        <p><b>Тестовые креденшелы:</b><br>
                        - user@gmail.com / password<br>
                        - admin@javaops.ru / admin</p>
                        """,
                contact = @Contact(url = "http://localhost:8080/api", name = "IlyaM", email = "admin@admin.ru")),
        security = @SecurityRequirement(name = "basicAuth")
)
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("REST API")
                .pathsToMatch("/api/**")
                .build();
    }
}
