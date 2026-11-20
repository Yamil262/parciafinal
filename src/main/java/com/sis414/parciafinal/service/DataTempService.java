package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.DataTempDto;
import com.sis414.parciafinal.entity.DataTemp;
import com.sis414.parciafinal.repository.DataTempRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DataTempService {

    private final DataTempRepository repository;

    public DataTempService(DataTempRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<DataTempDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public DataTempDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public DataTempDto create(DataTempDto dto) {
        DataTemp entity = new DataTemp();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public DataTempDto update(Long id, DataTempDto dto) {
        DataTemp entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public DataTemp getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DataTemp no encontrado: " + id));
    }

    public DataTempDto toDto(DataTemp e) {
        return new DataTempDto(
                e.getId(),
                e.getData()
        );
    }

    private void apply(DataTempDto dto, DataTemp e) {
        e.setData(dto.data());
    }
}
