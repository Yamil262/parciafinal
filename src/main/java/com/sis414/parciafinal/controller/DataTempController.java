package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.DataTempDto;
import com.sis414.parciafinal.service.DataTempService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
@Tag(name = "Localización BD")
public class DataTempController {

    private final DataTempService service;

    public DataTempController(DataTempService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "DataTemp - Listar")
    public List<DataTempDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "DataTemp - Obtener por id")
    public DataTempDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "DataTemp - Nuevo")
    public DataTempDto create(@RequestBody DataTempDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "DataTemp - Modificar")
    public DataTempDto update(@PathVariable Long id, @RequestBody DataTempDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "DataTemp - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
