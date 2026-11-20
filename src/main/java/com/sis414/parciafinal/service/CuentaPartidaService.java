package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.CuentaPartidaDto;
import com.sis414.parciafinal.entity.CuentaPartida;
import com.sis414.parciafinal.repository.CuentaPartidaRepository;
import com.sis414.parciafinal.repository.GrupoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CuentaPartidaService {

    private final CuentaPartidaRepository repository;
    private final GrupoRepository grupoRepository;

    public CuentaPartidaService(CuentaPartidaRepository repository, GrupoRepository grupoRepository) {
        this.repository = repository;
        this.grupoRepository = grupoRepository;
    }

    @Transactional(readOnly = true)
    public List<CuentaPartidaDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public CuentaPartidaDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public CuentaPartidaDto create(CuentaPartidaDto dto) {
        CuentaPartida entity = new CuentaPartida();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public CuentaPartidaDto update(Long id, CuentaPartidaDto dto) {
        CuentaPartida entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public CuentaPartida getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CuentaPartida no encontrado: " + id));
    }

    public CuentaPartidaDto toDto(CuentaPartida e) {
        return new CuentaPartidaDto(
                e.getId(),
                e.getPartida(),
                e.getGestion(),
                e.getGrupo() == null ? null : e.getGrupo().getId()
        );
    }

    private void apply(CuentaPartidaDto dto, CuentaPartida e) {
        e.setPartida(dto.partida());
        e.setGestion(dto.gestion());
        e.setGrupo(dto.grupoId() == null ? null : grupoRepository.getReferenceById(dto.grupoId()));
    }
}
