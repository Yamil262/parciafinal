package com.sis414.parciafinal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI parciafinalOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("V.S.I.A.F - Sistema de Activos Fijos")
                .version("1.0")
                .description("API REST sobre los datos migrados desde los archivos DBF del sistema de activos fijos. "
                        + "Los DTO de salida no exponen datos sensibles (contraseñas, CI completo)."));
    }
}
