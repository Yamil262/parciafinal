package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.OficinaDto;
import com.sis414.parciafinal.entity.Oficina;
import com.sis414.parciafinal.repository.OficinaRepository;
import com.sis414.parciafinal.repository.UnidadAdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OficinaService {

    private final OficinaRepository repository;
    private final UnidadAdminRepository unidadAdminRepository;

    public OficinaService(OficinaRepository repository, UnidadAdminRepository unidadAdminRepository) {
        this.repository = repository;
        this.unidadAdminRepository = unidadAdminRepository;
    }

    @Transactional(readOnly = true)
    public List<OficinaDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public OficinaDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public OficinaDto create(OficinaDto dto) {
        Oficina entity = new Oficina();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public OficinaDto update(Long id, OficinaDto dto) {
        Oficina entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public Oficina getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Oficina no encontrado: " + id));
    }

    public OficinaDto toDto(Oficina e) {
        return new OficinaDto(
                e.getId(),
                e.getCodOfic(),
                e.getNomOfic(),
                e.getObserv(),
                e.getFeult(),
                e.getUsuar(),
                e.getApiEstado(),
                e.getUnidad() == null ? null : e.getUnidad().getId()
        );
    }

    private void apply(OficinaDto dto, Oficina e) {
        e.setCodOfic(dto.codOfic());
        e.setNomOfic(dto.nomOfic());
        e.setObserv(dto.observ());
        e.setFeult(dto.feult());
        e.setUsuar(dto.usuar());
        e.setApiEstado(dto.apiEstado());
        e.setUnidad(dto.unidadId() == null ? null : unidadAdminRepository.getReferenceById(dto.unidadId()));
    }
}
