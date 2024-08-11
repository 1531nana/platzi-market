package com.platzi.market.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableWebMvc // Habilitar swagger 2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        // Un docket es un objeto que contiene todas las propiedades personalizables que usted configura y que Swagger utiliza para generar la documentación.
        // param           Tipo de documentación
        return new Docket(DocumentationType.SWAGGER_2)
                // Qué queremos que exporte o exponga en la documentación
                .select()
                // Indicarle el paquete que queremos exponer
                .apis(RequestHandlerSelectors.basePackage("com.platzi.market.web.controller"))
                // Construir la respueta
                .build();
    }

}
