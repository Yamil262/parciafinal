//package com.sis414.parciafinal.controller;
//
//import com.sis414.parciafinal.dto.AcercaDto;
//import com.sis414.parciafinal.dto.ContextoDto;
//import com.sis414.parciafinal.service.SistemaService;
//import io.swagger.v3.oas.annotations.Hidden;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.web.bind.annotation.*;
//@Hidden
//@RestController
//@RequestMapping("/api/sistema")
//@Tag(name = "Menú principal")
//public class SistemaController {
//
//    private final SistemaService service;
//
//    public SistemaController(SistemaService service) {
//        this.service = service;
//    }
//
//    @GetMapping("/contexto")
//    @Operation(summary = "Cabecera: ENTIDAD, UNIDAD y BACKUPS")
//    public ContextoDto contexto() {
//        return service.contexto();
//    }
//
//    @GetMapping("/acerca")
//    @Operation(summary = "Acerca de...")
//    public AcercaDto acerca() {
//        return service.acerca();
//    }
//}
