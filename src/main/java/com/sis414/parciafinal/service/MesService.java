package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.MesDto;
import com.sis414.parciafinal.entity.Mes;
import com.sis414.parciafinal.repository.MesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MesService {

    private final MesRepository repository;

    public MesService(MesRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<MesDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public MesDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public MesDto create(MesDto dto) {
        Mes entity = new Mes();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public MesDto update(Long id, MesDto dto) {
        Mes entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public Mes getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mes no encontrado: " + id));
    }

    public MesDto toDto(Mes e) {
        return new MesDto(
                e.getId(),
                e.getMes(),
                e.getNomMes()
        );
    }

    private void apply(MesDto dto, Mes e) {
        e.setMes(dto.mes());
        e.setNomMes(dto.nomMes());
    }
}
