package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.OrgUser;
import com.sis414.parciafinal.repository.OrgUserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** OrgUser: tabla ORGUSER.DBF. La clave es la del DBF (type, idUser, name), no hay "id" artificial. */
@RestController
@RequestMapping("/api/org-user")
@Tag(name = "Administradores")
public class OrgUserController {

    private final OrgUserRepository repository;

    public OrgUserController(OrgUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "OrgUser - Listar")
    public List<OrgUser> findAll() {
        return repository.findAll();
    }

    @GetMapping("/item")
    @Operation(summary = "OrgUser - Obtener por clave")
    public OrgUser findByKey(@RequestParam(name = "type", defaultValue = "") String type, @RequestParam(name = "idUser", defaultValue = "") String idUser, @RequestParam(name = "name", defaultValue = "") String name) {
        return repository.findById(new OrgUser.Key(type, idUser, name))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "OrgUser no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "OrgUser - Nuevo")
    public OrgUser create(@RequestBody OrgUser body) {
        if (body.getType() == null || body.getIdUser() == null || body.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: type, idUser, name");
        }
        if (repository.existsById(new OrgUser.Key(body.getType(), body.getIdUser(), body.getName()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "OrgUser ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/item")
    @Operation(summary = "OrgUser - Modificar")
    public OrgUser update(@RequestParam(name = "type", defaultValue = "") String type, @RequestParam(name = "idUser", defaultValue = "") String idUser, @RequestParam(name = "name", defaultValue = "") String name, @RequestBody OrgUser body) {
        if (!repository.existsById(new OrgUser.Key(type, idUser, name))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrgUser no encontrado");
        }
        body.setType(type);
        body.setIdUser(idUser);
        body.setName(name);
        return repository.save(body);
    }

    @DeleteMapping("/item")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "OrgUser - Eliminar")
    public void delete(@RequestParam(name = "type", defaultValue = "") String type, @RequestParam(name = "idUser", defaultValue = "") String idUser, @RequestParam(name = "name", defaultValue = "") String name) {
        if (!repository.existsById(new OrgUser.Key(type, idUser, name))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrgUser no encontrado");
        }
        repository.deleteById(new OrgUser.Key(type, idUser, name));
    }
}
