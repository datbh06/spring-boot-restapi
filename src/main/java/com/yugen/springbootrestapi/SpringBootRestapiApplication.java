package com.yugen.springbootrestapi;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Blog App RestAPI",
                description = "Spring Boot Documentation Blog API v1.0",
                version = "v1.0",
                contact = @Contact(
                        name = "Datbh",
                        email = "bdat1606@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot Documentation Blog API v1.0",
                url = "https://github.com/datbh06/spring-boot-restapi/tree/master"
        )
)
public class SpringBootRestapiApplication {

    /**
     * Creates a new ModelMapper bean.
     *
     * @return the created ModelMapper bean
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestapiApplication.class, args);
    }

}
