
package com.sis414.parciafinal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;

@Component
public class SeedDataLoader implements ApplicationRunner {

    private static final Logger log =
            LoggerFactory.getLogger(SeedDataLoader.class);

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final boolean enabled;

    public SeedDataLoader(
            DataSource dataSource,
            JdbcTemplate jdbcTemplate,
            @Value("${parciafinal.seed.enabled:true}") boolean enabled) {

        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.enabled = enabled;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (!enabled) {
            log.info("Carga inicial deshabilitada.");
            return;
        }

        // Comprobar si ya existen datos en tablas principales
        Integer unidades = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM unidadadmin",
                Integer.class
        );

        Integer meses = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM mes",
                Integer.class
        );

        if ((unidades != null && unidades > 0) ||
                (meses != null && meses > 0)) {

            log.info("La base ya contiene datos.");
            log.info("Se omite la carga inicial para evitar duplicados.");
            return;
        }

        String databaseProduct;

        try (Connection connection = dataSource.getConnection()) {
            databaseProduct =
                    connection.getMetaData().getDatabaseProductName();
        }

        log.info("Base de datos detectada: {}", databaseProduct);

        ClassPathResource seedResource =
                new ClassPathResource("seed/seed.sql");

        String sql = seedResource.getContentAsString(
                StandardCharsets.UTF_8
        );

        if (databaseProduct.toLowerCase().contains("postgresql")) {

            sql = sql.replaceAll(
                    "(?im)^\\s*SET\\s+REFERENTIAL_INTEGRITY\\s+(?:FALSE|TRUE)\\s*;\\s*$",
                    ""
            );

            log.info("Se omitieron instrucciones exclusivas de H2.");
        }

        log.info("Cargando datos iniciales...");

        ResourceDatabasePopulator populator =
                new ResourceDatabasePopulator();

        populator.addScript(
                new ByteArrayResource(
                        sql.getBytes(StandardCharsets.UTF_8)
                )
        );

        populator.execute(dataSource);

        log.info("Carga inicial completada.");
    }
}
