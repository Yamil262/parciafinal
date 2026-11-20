package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.ReporteDto;
import com.sis414.parciafinal.entity.Reporte;
import com.sis414.parciafinal.repository.ReporteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReporteService {

    private final ReporteRepository repository;

    public ReporteService(ReporteRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ReporteDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public ReporteDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public ReporteDto create(ReporteDto dto) {
        Reporte entity = new Reporte();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public ReporteDto update(Long id, ReporteDto dto) {
        Reporte entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public Reporte getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporte no encontrado: " + id));
    }

    public ReporteDto toDto(Reporte e) {
        return new ReporteDto(
                e.getId(),
                e.getReportName(),
                e.getContents(),
                e.getModType(),
                e.getNotes()
        );
    }

    private void apply(ReporteDto dto, Reporte e) {
        e.setReportName(dto.reportName());
        e.setContents(dto.contents());
        e.setModType(dto.modType());
        e.setNotes(dto.notes());
    }
}
