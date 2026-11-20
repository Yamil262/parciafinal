package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.ResponsableDto;
import com.sis414.parciafinal.entity.Responsable;
import com.sis414.parciafinal.repository.DepartamentoRepository;
import com.sis414.parciafinal.repository.OficinaRepository;
import com.sis414.parciafinal.repository.ResponsableRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ResponsableService {

    private final ResponsableRepository repository;
    private final DepartamentoRepository departamentoRepository;
    private final OficinaRepository oficinaRepository;

    public ResponsableService(ResponsableRepository repository, DepartamentoRepository departamentoRepository, OficinaRepository oficinaRepository) {
        this.repository = repository;
        this.departamentoRepository = departamentoRepository;
        this.oficinaRepository = oficinaRepository;
    }

    @Transactional(readOnly = true)
    public List<ResponsableDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public ResponsableDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public ResponsableDto create(ResponsableDto dto) {
        Responsable entity = new Responsable();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public ResponsableDto update(Long id, ResponsableDto dto) {
        Responsable entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public Responsable getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsable no encontrado: " + id));
    }

    public ResponsableDto toDto(Responsable e) {
        return new ResponsableDto(
                e.getId(),
                e.getCodResp(),
                e.getNomResp(),
                e.getCargo(),
                e.getObserv(),
                e.getCi(),
                e.getFeult(),
                e.getUsuar(),
                e.getApiEstado(),
                e.getOficina() == null ? null : e.getOficina().getId(),
                e.getDepartamentoExp() == null ? null : e.getDepartamentoExp().getId()
        );
    }

    private void apply(ResponsableDto dto, Responsable e) {
        e.setCodResp(dto.codResp());
        e.setNomResp(dto.nomResp());
        e.setCargo(dto.cargo());
        e.setObserv(dto.observ());
        e.setCi(dto.ci());
        e.setFeult(dto.feult());
        e.setUsuar(dto.usuar());
        e.setApiEstado(dto.apiEstado());
        e.setOficina(dto.oficinaId() == null ? null : oficinaRepository.getReferenceById(dto.oficinaId()));
        e.setDepartamentoExp(dto.departamentoExpId() == null ? null : departamentoRepository.getReferenceById(dto.departamentoExpId()));
    }
}
