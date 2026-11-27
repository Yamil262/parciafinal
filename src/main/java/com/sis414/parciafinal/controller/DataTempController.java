package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.DataTemp;
import com.sis414.parciafinal.repository.DataTempRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Data: tabla DATA.DBF. La clave es la del DBF (data), no hay "id" artificial. */
@RestController
@RequestMapping("/api/data")
@Tag(name = "Localización BD")
public class DataTempController {

    private final DataTempRepository repository;

    public DataTempController(DataTempRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Data - Listar")
    public List<DataTemp> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{data}")
    @Operation(summary = "Data - Obtener por clave")
    public DataTemp findByKey(@PathVariable("data") String data) {
        return repository.findById(data)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Data - Nuevo")
    public DataTemp create(@RequestBody DataTemp body) {
        if (body.getData() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: data");
        }
        if (repository.existsById(body.getData())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{data}")
    @Operation(summary = "Data - Modificar")
    public DataTemp update(@PathVariable("data") String data, @RequestBody DataTemp body) {
        if (!repository.existsById(data)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data no encontrado");
        }
        body.setData(data);
        return repository.save(body);
    }

    @DeleteMapping("/{data}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Data - Eliminar")
    public void delete(@PathVariable("data") String data) {
        if (!repository.existsById(data)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data no encontrado");
        }
        repository.deleteById(data);
    }
}
