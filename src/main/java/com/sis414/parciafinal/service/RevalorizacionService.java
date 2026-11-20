package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.RevalorizacionDto;
import com.sis414.parciafinal.entity.Revalorizacion;
import com.sis414.parciafinal.repository.ActivoFijoRepository;
import com.sis414.parciafinal.repository.RevalorizacionRepository;
import com.sis414.parciafinal.repository.UnidadAdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RevalorizacionService {

    private final RevalorizacionRepository repository;
    private final ActivoFijoRepository activoFijoRepository;
    private final UnidadAdminRepository unidadAdminRepository;

    public RevalorizacionService(RevalorizacionRepository repository, ActivoFijoRepository activoFijoRepository, UnidadAdminRepository unidadAdminRepository) {
        this.repository = repository;
        this.activoFijoRepository = activoFijoRepository;
        this.unidadAdminRepository = unidadAdminRepository;
    }

    @Transactional(readOnly = true)
    public List<RevalorizacionDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public RevalorizacionDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public RevalorizacionDto create(RevalorizacionDto dto) {
        Revalorizacion entity = new Revalorizacion();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public RevalorizacionDto update(Long id, RevalorizacionDto dto) {
        Revalorizacion entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public Revalorizacion getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Revalorizacion no encontrado: " + id));
    }

    public RevalorizacionDto toDto(Revalorizacion e) {
        return new RevalorizacionDto(
                e.getId(),
                e.getCodigo(),
                e.getCosto(),
                e.getDepacuAnt(),
                e.getDiaReval(),
                e.getDia(),
                e.getMesReval(),
                e.getMes(),
                e.getAnoReval(),
                e.getAno(),
                e.getVidaUtil(),
                e.getResolucion(),
                e.getObserv(),
                e.getUsuar(),
                e.getFeult(),
                e.getDepacu(),
                e.getActua(),
                e.getDepGestion(),
                e.getFecMod(),
                e.getUsuMod(),
                e.getUnidad() == null ? null : e.getUnidad().getId(),
                e.getActivo() == null ? null : e.getActivo().getId()
        );
    }

    private void apply(RevalorizacionDto dto, Revalorizacion e) {
        e.setCodigo(dto.codigo());
        e.setCosto(dto.costo());
        e.setDepacuAnt(dto.depacuAnt());
        e.setDiaReval(dto.diaReval());
        e.setDia(dto.dia());
        e.setMesReval(dto.mesReval());
        e.setMes(dto.mes());
        e.setAnoReval(dto.anoReval());
        e.setAno(dto.ano());
        e.setVidaUtil(dto.vidaUtil());
        e.setResolucion(dto.resolucion());
        e.setObserv(dto.observ());
        e.setUsuar(dto.usuar());
        e.setFeult(dto.feult());
        e.setDepacu(dto.depacu());
        e.setActua(dto.actua());
        e.setDepGestion(dto.depGestion());
        e.setFecMod(dto.fecMod());
        e.setUsuMod(dto.usuMod());
        e.setUnidad(dto.unidadId() == null ? null : unidadAdminRepository.getReferenceById(dto.unidadId()));
        e.setActivo(dto.activoId() == null ? null : activoFijoRepository.getReferenceById(dto.activoId()));
    }
}
