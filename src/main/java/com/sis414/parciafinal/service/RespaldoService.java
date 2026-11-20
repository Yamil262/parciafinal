package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.RespaldoDto;
import com.sis414.parciafinal.entity.Respaldo;
import com.sis414.parciafinal.repository.RespaldoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RespaldoService {

    private final RespaldoRepository repository;

    public RespaldoService(RespaldoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<RespaldoDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public RespaldoDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public RespaldoDto create(RespaldoDto dto) {
        Respaldo entity = new Respaldo();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public RespaldoDto update(Long id, RespaldoDto dto) {
        Respaldo entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public Respaldo getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Respaldo no encontrado: " + id));
    }

    public RespaldoDto toDto(Respaldo e) {
        return new RespaldoDto(
                e.getId(),
                e.getDia(),
                e.getHora(),
                e.getArchivo(),
                e.getObserv(),
                e.getUsuar(),
                e.getFeult()
        );
    }

    private void apply(RespaldoDto dto, Respaldo e) {
        e.setDia(dto.dia());
        e.setHora(dto.hora());
        e.setArchivo(dto.archivo());
        e.setObserv(dto.observ());
        e.setUsuar(dto.usuar());
        e.setFeult(dto.feult());
    }
}
