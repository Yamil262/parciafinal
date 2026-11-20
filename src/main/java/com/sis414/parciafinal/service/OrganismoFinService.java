package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.OrganismoFinDto;
import com.sis414.parciafinal.entity.OrganismoFin;
import com.sis414.parciafinal.repository.OrganismoFinRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrganismoFinService {

    private final OrganismoFinRepository repository;

    public OrganismoFinService(OrganismoFinRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<OrganismoFinDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public OrganismoFinDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public OrganismoFinDto create(OrganismoFinDto dto) {
        OrganismoFin entity = new OrganismoFin();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public OrganismoFinDto update(Long id, OrganismoFinDto dto) {
        OrganismoFin entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public OrganismoFin getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "OrganismoFin no encontrado: " + id));
    }

    public OrganismoFinDto toDto(OrganismoFin e) {
        return new OrganismoFinDto(
                e.getId(),
                e.getGestion(),
                e.getCodigo(),
                e.getDescripcion(),
                e.getSigla()
        );
    }

    private void apply(OrganismoFinDto dto, OrganismoFin e) {
        e.setGestion(dto.gestion());
        e.setCodigo(dto.codigo());
        e.setDescripcion(dto.descripcion());
        e.setSigla(dto.sigla());
    }
}
