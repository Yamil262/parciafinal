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
public class EstEntService {

    private final EstEntRepository estEntRepository;
    private final EntidadesRepository entidadesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EstEntService(EstEntRepository estEntRepository, EntidadesRepository entidadesRepository) {
        this.estEntRepository = estEntRepository;
        this.entidadesRepository = entidadesRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<EstEntResponse> listar(Pageable pageable) {
        Page<EstEnt> page = estEntRepository.findAll(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public EstEntResponse obtener(Integer gestion, Integer sectorEnt, Integer subsecEnt, Integer areaEnt, Integer subAreaEnt, Integer nivelInst) {
        return toResponse(buscar(gestion, sectorEnt, subsecEnt, areaEnt, subAreaEnt, nivelInst));
    }

    private EstEnt buscar(Integer gestion, Integer sectorEnt, Integer subsecEnt, Integer areaEnt, Integer subAreaEnt, Integer nivelInst) {
        return estEntRepository.findById(new EstEntId(gestion, sectorEnt, subsecEnt, areaEnt, subAreaEnt, nivelInst))
                .orElseThrow(() -> new NotFoundException("No se encontró estructura institucional con clave [" + String.valueOf(gestion) + ", " + String.valueOf(sectorEnt) + ", " + String.valueOf(subsecEnt) + ", " + String.valueOf(areaEnt) + ", " + String.valueOf(subAreaEnt) + ", " + String.valueOf(nivelInst) + "]"));
    }

    @Transactional
    public EstEntResponse crear(EstEntRequest request) {
        if (request.gestion() == null) {
            throw new BadRequestException("El campo 'gestion' es obligatorio para crear estructura institucional");
        }
        if (request.sectorEnt() == null) {
            throw new BadRequestException("El campo 'sectorEnt' es obligatorio para crear estructura institucional");
        }
        if (request.subsecEnt() == null) {
            throw new BadRequestException("El campo 'subsecEnt' es obligatorio para crear estructura institucional");
        }
        if (request.areaEnt() == null) {
            throw new BadRequestException("El campo 'areaEnt' es obligatorio para crear estructura institucional");
        }
        if (request.subAreaEnt() == null) {
            throw new BadRequestException("El campo 'subAreaEnt' es obligatorio para crear estructura institucional");
        }
        if (request.nivelInst() == null) {
            throw new BadRequestException("El campo 'nivelInst' es obligatorio para crear estructura institucional");
        }
        if (estEntRepository.existsById(new EstEntId(request.gestion(), request.sectorEnt(), request.subsecEnt(), request.areaEnt(), request.subAreaEnt(), request.nivelInst()))) {
            throw new ConflictException("Ya existe estructura institucional con clave [" + String.valueOf(request.gestion()) + ", " + String.valueOf(request.sectorEnt()) + ", " + String.valueOf(request.subsecEnt()) + ", " + String.valueOf(request.areaEnt()) + ", " + String.valueOf(request.subAreaEnt()) + ", " + String.valueOf(request.nivelInst()) + "]");
        }
        EstEnt e = new EstEnt();
        e.setGestion(request.gestion());
        e.setSectorEnt(request.sectorEnt());
        e.setSubsecEnt(request.subsecEnt());
        e.setAreaEnt(request.areaEnt());
        e.setSubAreaEnt(request.subAreaEnt());
        e.setNivelInst(request.nivelInst());
        aplicar(e, request);
        validarReferencias(e);
        estEntRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public EstEntResponse actualizar(Integer gestion, Integer sectorEnt, Integer subsecEnt, Integer areaEnt, Integer subAreaEnt, Integer nivelInst, EstEntRequest request) {
        EstEnt e = buscar(gestion, sectorEnt, subsecEnt, areaEnt, subAreaEnt, nivelInst);
        aplicar(e, request);
        validarReferencias(e);
        estEntRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(Integer gestion, Integer sectorEnt, Integer subsecEnt, Integer areaEnt, Integer subAreaEnt, Integer nivelInst) {
        EstEnt e = buscar(gestion, sectorEnt, subsecEnt, areaEnt, subAreaEnt, nivelInst);
        if (entidadesRepository.existsByGestionAndSectorEntAndSubsecEntAndAreaEntAndSubAreaEntAndNivelInst(e.getGestion(), e.getSectorEnt(), e.getSubsecEnt(), e.getAreaEnt(), e.getSubAreaEnt(), e.getNivelInst())) {
            throw new ConflictException("No se puede eliminar estructura institucional: tiene entidades asociados");
        }
        estEntRepository.delete(e);
    }

    private void aplicar(EstEnt e, EstEntRequest r) {
        e.setDesEstruc(r.desEstruc());
        e.setSiglaEstru(r.siglaEstru());
        e.setApropiable(r.apropiable());
    }

    private void validarReferencias(EstEnt e) {
    }

    private EstEntResponse toResponse(EstEnt e) {
        return new EstEntResponse(
                e.getGestion(),
                e.getSectorEnt(),
                e.getSubsecEnt(),
                e.getAreaEnt(),
                e.getSubAreaEnt(),
                e.getNivelInst(),
                e.getDesEstruc(),
                e.getSiglaEstru(),
                e.getApropiable()
        );
    }
}
