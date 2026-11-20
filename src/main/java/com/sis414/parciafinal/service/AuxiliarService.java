package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.AuxiliarDto;
import com.sis414.parciafinal.entity.Auxiliar;
import com.sis414.parciafinal.repository.AuxiliarRepository;
import com.sis414.parciafinal.repository.GrupoRepository;
import com.sis414.parciafinal.repository.UnidadAdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuxiliarService {

    private final AuxiliarRepository repository;
    private final GrupoRepository grupoRepository;
    private final UnidadAdminRepository unidadAdminRepository;

    public AuxiliarService(AuxiliarRepository repository, GrupoRepository grupoRepository, UnidadAdminRepository unidadAdminRepository) {
        this.repository = repository;
        this.grupoRepository = grupoRepository;
        this.unidadAdminRepository = unidadAdminRepository;
    }

    @Transactional(readOnly = true)
    public List<AuxiliarDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public AuxiliarDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public AuxiliarDto create(AuxiliarDto dto) {
        Auxiliar entity = new Auxiliar();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public AuxiliarDto update(Long id, AuxiliarDto dto) {
        Auxiliar entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public Auxiliar getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Auxiliar no encontrado: " + id));
    }

    public AuxiliarDto toDto(Auxiliar e) {
        return new AuxiliarDto(
                e.getId(),
                e.getCodAux(),
                e.getNomAux(),
                e.getObserv(),
                e.getFeult(),
                e.getUsuar(),
                e.getUnidad() == null ? null : e.getUnidad().getId(),
                e.getGrupo() == null ? null : e.getGrupo().getId()
        );
    }

    private void apply(AuxiliarDto dto, Auxiliar e) {
        e.setCodAux(dto.codAux());
        e.setNomAux(dto.nomAux());
        e.setObserv(dto.observ());
        e.setFeult(dto.feult());
        e.setUsuar(dto.usuar());
        e.setUnidad(dto.unidadId() == null ? null : unidadAdminRepository.getReferenceById(dto.unidadId()));
        e.setGrupo(dto.grupoId() == null ? null : grupoRepository.getReferenceById(dto.grupoId()));
    }
}
