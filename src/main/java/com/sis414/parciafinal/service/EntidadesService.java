package com.sis414.parciafinal.service;

import com.sis414.parciafinal.common.PageResponse;
import com.sis414.parciafinal.dto.*;
import com.sis414.parciafinal.entity.*;
import com.sis414.parciafinal.exception.*;
import com.sis414.parciafinal.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class EntidadesService {

    private final EntidadesRepository entidadesRepository;
    private final EstEntRepository estEntRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EntidadesService(EntidadesRepository entidadesRepository, EstEntRepository estEntRepository) {
        this.entidadesRepository = entidadesRepository;
        this.estEntRepository = estEntRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<EntidadesResponse> listar(Pageable pageable) {
        Page<Entidades> page = entidadesRepository.findAll(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public EntidadesResponse obtener(Integer gestion, String entidad) {
        return toResponse(buscar(gestion, entidad));
    }

    private Entidades buscar(Integer gestion, String entidad) {
        return entidadesRepository.findById(new EntidadesId(gestion, entidad))
                .orElseThrow(() -> new NotFoundException("No se encontró entidad con clave [" + String.valueOf(gestion) + ", " + String.valueOf(entidad) + "]"));
    }

    @Transactional
    public EntidadesResponse crear(EntidadesRequest request) {
        if (request.gestion() == null) {
            throw new BadRequestException("El campo 'gestion' es obligatorio para crear entidad");
        }
        if (request.entidad() == null || request.entidad().isBlank()) {
            throw new BadRequestException("El campo 'entidad' es obligatorio para crear entidad");
        }
        if (entidadesRepository.existsById(new EntidadesId(request.gestion(), request.entidad()))) {
            throw new ConflictException("Ya existe entidad con clave [" + String.valueOf(request.gestion()) + ", " + String.valueOf(request.entidad()) + "]");
        }
        Entidades e = new Entidades();
        e.setGestion(request.gestion());
        e.setEntidad(request.entidad());
        aplicar(e, request);
        validarReferencias(e);
        entidadesRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public EntidadesResponse actualizar(Integer gestion, String entidad, EntidadesRequest request) {
        Entidades e = buscar(gestion, entidad);
        aplicar(e, request);
        validarReferencias(e);
        entidadesRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(Integer gestion, String entidad) {
        Entidades e = buscar(gestion, entidad);
        entidadesRepository.delete(e);
    }

    private void aplicar(Entidades e, EntidadesRequest r) {
        e.setDescEnt(r.descEnt());
        e.setSiglaEnt(r.siglaEnt());
        e.setSectorEnt(r.sectorEnt());
        e.setSubsecEnt(r.subsecEnt());
        e.setAreaEnt(r.areaEnt());
        e.setSubAreaEnt(r.subAreaEnt());
        e.setNivelInst(r.nivelInst());
    }

    private void validarReferencias(Entidades e) {
        if (e.getGestion() != null && e.getGestion() != 0 && e.getSectorEnt() != null && e.getSectorEnt() != 0 && e.getSubsecEnt() != null && e.getSubsecEnt() != 0 && e.getAreaEnt() != null && e.getAreaEnt() != 0 && e.getSubAreaEnt() != null && e.getSubAreaEnt() != 0 && e.getNivelInst() != null && e.getNivelInst() != 0 && !estEntRepository.existsById(new EstEntId(e.getGestion(), e.getSectorEnt(), e.getSubsecEnt(), e.getAreaEnt(), e.getSubAreaEnt(), e.getNivelInst()))) {
            throw new BadRequestException("No existe estructura institucional con clave [" + String.valueOf(e.getGestion()) + ", " + String.valueOf(e.getSectorEnt()) + ", " + String.valueOf(e.getSubsecEnt()) + ", " + String.valueOf(e.getAreaEnt()) + ", " + String.valueOf(e.getSubAreaEnt()) + ", " + String.valueOf(e.getNivelInst()) + "]");
        }
    }

    private EntidadesResponse toResponse(Entidades e) {
        return new EntidadesResponse(
                e.getGestion(),
                e.getEntidad(),
                e.getDescEnt(),
                e.getSiglaEnt(),
                e.getSectorEnt(),
                e.getSubsecEnt(),
                e.getAreaEnt(),
                e.getSubAreaEnt(),
                e.getNivelInst(),
                e.getEstructura() != null ? e.getEstructura().getDesEstruc() : null
        );
    }
}
