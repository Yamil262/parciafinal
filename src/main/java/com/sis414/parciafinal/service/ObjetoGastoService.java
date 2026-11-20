package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.ObjetoGastoDto;
import com.sis414.parciafinal.entity.ObjetoGasto;
import com.sis414.parciafinal.repository.ObjetoGastoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ObjetoGastoService {

    private final ObjetoGastoRepository repository;

    public ObjetoGastoService(ObjetoGastoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ObjetoGastoDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public ObjetoGastoDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public ObjetoGastoDto create(ObjetoGastoDto dto) {
        ObjetoGasto entity = new ObjetoGasto();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public ObjetoGastoDto update(Long id, ObjetoGastoDto dto) {
        ObjetoGasto entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public ObjetoGasto getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ObjetoGasto no encontrado: " + id));
    }

    public ObjetoGastoDto toDto(ObjetoGasto e) {
        return new ObjetoGastoDto(
                e.getId(),
                e.getGestion(),
                e.getPartida(),
                e.getDescrip()
        );
    }

    private void apply(ObjetoGastoDto dto, ObjetoGasto e) {
        e.setGestion(dto.gestion());
        e.setPartida(dto.partida());
        e.setDescrip(dto.descrip());
    }
}
