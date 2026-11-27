package com.sis414.parciafinal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("V.S.I.A.F - Sistema de Activos Fijos")
                        .version("3.2")
                        .description("Parcial Final. API REST de las 24 tablas del VSIAF; cada grupo replica un boton del MENU PRINCIPAL."))
                .tags(List.of(
                       new Tag().name("Menú principal").description("SISTEMA DE ACTIVOS FIJOS: ENTIDAD, UNIDAD, BACKUPS, Acerca de..."),
                        new Tag().name("Activos Fijos").description("ADMINISTRACION DE ACTIVOS FIJOS: Nuevo, Modificar, Duplicar, Transferir, Buscar, Eliminar, Aprobar, Primero/Anterior/Siguiente/Ultimo"),
                        new Tag().name("Grupos y Auxiliares").description("GRUPO CONTABLE: vida util, deprecia, actualiza y auxiliares"),
                        new Tag().name("Oficinas y Responsables").description("OFICINA: oficinas y responsables, Activar / Inactivar"),
                        new Tag().name("Generar Reportes").description("REPORTES A MEDIDA: filtros, Todos, Consolidado, Calculados A, Hoy"),
                        new Tag().name("Administradores").description("ADMINISTRACION DE RECURSOS: gestion, Importar/Exportar, Indices UFV, Seguridad, Re-indexar, Migrador"),
                        new Tag().name("Iniciar sesión").description("INGRESE SU IDENTIFICACION: Login"),
                        new Tag().name("Respaldos BD").description("ADMINISTRACION DE BACKUPS: Respaldar, Restaurar, Copiar a, Copiar de, Borrar"),
                        new Tag().name("Administrar Unidad").description("ADMINISTRACION UNIDAD ADMINISTRATIVA: Nuevo, Editar, Eliminar, Seleccionar"),
                        new Tag().name("Localización BD").description("LOCALIZACION DE ARCHIVOS: directorio de la base de datos")));
    }
}
