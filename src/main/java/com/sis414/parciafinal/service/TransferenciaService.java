package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.TransferenciaDto;
import com.sis414.parciafinal.entity.Transferencia;
import com.sis414.parciafinal.repository.ActivoFijoRepository;
import com.sis414.parciafinal.repository.OficinaRepository;
import com.sis414.parciafinal.repository.ResponsableRepository;
import com.sis414.parciafinal.repository.TransferenciaRepository;
import com.sis414.parciafinal.repository.UnidadAdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransferenciaService {

    private final TransferenciaRepository repository;
    private final ActivoFijoRepository activoFijoRepository;
    private final OficinaRepository oficinaRepository;
    private final ResponsableRepository responsableRepository;
    private final UnidadAdminRepository unidadAdminRepository;

    public TransferenciaService(TransferenciaRepository repository, ActivoFijoRepository activoFijoRepository, OficinaRepository oficinaRepository, ResponsableRepository responsableRepository, UnidadAdminRepository unidadAdminRepository) {
        this.repository = repository;
        this.activoFijoRepository = activoFijoRepository;
        this.oficinaRepository = oficinaRepository;
        this.responsableRepository = responsableRepository;
        this.unidadAdminRepository = unidadAdminRepository;
    }

    @Transactional(readOnly = true)
    public List<TransferenciaDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public TransferenciaDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public TransferenciaDto create(TransferenciaDto dto) {
        Transferencia entity = new Transferencia();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public TransferenciaDto update(Long id, TransferenciaDto dto) {
        Transferencia entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public Transferencia getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transferencia no encontrado: " + id));
    }

    public TransferenciaDto toDto(Transferencia e) {
        return new TransferenciaDto(
                e.getId(),
                e.getCodigo(),
                e.getFeult(),
                e.getUsuar(),
                e.getUnidad() == null ? null : e.getUnidad().getId(),
                e.getActivo() == null ? null : e.getActivo().getId(),
                e.getOficina() == null ? null : e.getOficina().getId(),
                e.getResponsable() == null ? null : e.getResponsable().getId(),
                e.getUnidadInicial() == null ? null : e.getUnidadInicial().getId()
        );
    }

    private void apply(TransferenciaDto dto, Transferencia e) {
        e.setCodigo(dto.codigo());
        e.setFeult(dto.feult());
        e.setUsuar(dto.usuar());
        e.setUnidad(dto.unidadId() == null ? null : unidadAdminRepository.getReferenceById(dto.unidadId()));
        e.setActivo(dto.activoId() == null ? null : activoFijoRepository.getReferenceById(dto.activoId()));
        e.setOficina(dto.oficinaId() == null ? null : oficinaRepository.getReferenceById(dto.oficinaId()));
        e.setResponsable(dto.responsableId() == null ? null : responsableRepository.getReferenceById(dto.responsableId()));
        e.setUnidadInicial(dto.unidadInicialId() == null ? null : unidadAdminRepository.getReferenceById(dto.unidadInicialId()));
    }
}
