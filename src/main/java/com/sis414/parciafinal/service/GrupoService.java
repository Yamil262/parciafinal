package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.GrupoDto;
import com.sis414.parciafinal.entity.Grupo;
import com.sis414.parciafinal.repository.GrupoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GrupoService {

    private final GrupoRepository repository;

    public GrupoService(GrupoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<GrupoDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public GrupoDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public GrupoDto create(GrupoDto dto) {
        Grupo entity = new Grupo();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public GrupoDto update(Long id, GrupoDto dto) {
        Grupo entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public Grupo getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo no encontrado: " + id));
    }

    public GrupoDto toDto(Grupo e) {
        return new GrupoDto(
                e.getId(),
                e.getCodCont(),
                e.getNombre(),
                e.getVidaUtil(),
                e.getObserv(),
                e.getDepreciar(),
                e.getActualizar(),
                e.getFeult(),
                e.getUsuar()
        );
    }

    private void apply(GrupoDto dto, Grupo e) {
        e.setCodCont(dto.codCont());
        e.setNombre(dto.nombre());
        e.setVidaUtil(dto.vidaUtil());
        e.setObserv(dto.observ());
        e.setDepreciar(dto.depreciar());
        e.setActualizar(dto.actualizar());
        e.setFeult(dto.feult());
        e.setUsuar(dto.usuar());
    }
}
