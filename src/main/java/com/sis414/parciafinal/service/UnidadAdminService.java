package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.UnidadAdminDto;
import com.sis414.parciafinal.entity.UnidadAdmin;
import com.sis414.parciafinal.repository.EntidadRepository;
import com.sis414.parciafinal.repository.UnidadAdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UnidadAdminService {

    private final UnidadAdminRepository repository;
    private final EntidadRepository entidadRepository;

    public UnidadAdminService(UnidadAdminRepository repository, EntidadRepository entidadRepository) {
        this.repository = repository;
        this.entidadRepository = entidadRepository;
    }

    @Transactional(readOnly = true)
    public List<UnidadAdminDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public UnidadAdminDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public UnidadAdminDto create(UnidadAdminDto dto) {
        UnidadAdmin entity = new UnidadAdmin();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public UnidadAdminDto update(Long id, UnidadAdminDto dto) {
        UnidadAdmin entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public UnidadAdmin getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UnidadAdmin no encontrado: " + id));
    }

    public UnidadAdminDto toDto(UnidadAdmin e) {
        return new UnidadAdminDto(
                e.getId(),
                e.getUnidad(),
                e.getDescrip(),
                e.getCiudad(),
                e.getEstadoUni(),
                e.getEntidad() == null ? null : e.getEntidad().getId()
        );
    }

    private void apply(UnidadAdminDto dto, UnidadAdmin e) {
        e.setUnidad(dto.unidad());
        e.setDescrip(dto.descrip());
        e.setCiudad(dto.ciudad());
        e.setEstadoUni(dto.estadoUni());
        e.setEntidad(dto.entidadId() == null ? null : entidadRepository.getReferenceById(dto.entidadId()));
    }
}
