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
public class MotivoBajaService {

    private final MotivoBajaRepository motivoBajaRepository;
    private final ActivoBajaRepository activoBajaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public MotivoBajaService(MotivoBajaRepository motivoBajaRepository, ActivoBajaRepository activoBajaRepository) {
        this.motivoBajaRepository = motivoBajaRepository;
        this.activoBajaRepository = activoBajaRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<MotivoBajaResponse> listar(Pageable pageable) {
        Page<MotivoBaja> page = motivoBajaRepository.findAll(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public MotivoBajaResponse obtener(Integer codBaja) {
        return toResponse(buscar(codBaja));
    }

    private MotivoBaja buscar(Integer codBaja) {
        return motivoBajaRepository.findById(codBaja)
                .orElseThrow(() -> new NotFoundException("No se encontró motivo de baja con clave [" + String.valueOf(codBaja) + "]"));
    }

    @Transactional
    public MotivoBajaResponse crear(MotivoBajaRequest request) {
        if (request.codBaja() == null) {
            throw new BadRequestException("El campo 'codBaja' es obligatorio para crear motivo de baja");
        }
        if (motivoBajaRepository.existsById(request.codBaja())) {
            throw new ConflictException("Ya existe motivo de baja con clave [" + String.valueOf(request.codBaja()) + "]");
        }
        MotivoBaja e = new MotivoBaja();
        e.setCodBaja(request.codBaja());
        aplicar(e, request);
        validarReferencias(e);
        motivoBajaRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public MotivoBajaResponse actualizar(Integer codBaja, MotivoBajaRequest request) {
        MotivoBaja e = buscar(codBaja);
        aplicar(e, request);
        validarReferencias(e);
        motivoBajaRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(Integer codBaja) {
        MotivoBaja e = buscar(codBaja);
        if (activoBajaRepository.existsByCodBaja(e.getCodBaja())) {
            throw new ConflictException("No se puede eliminar motivo de baja: tiene activos dados de baja asociados");
        }
        motivoBajaRepository.delete(e);
    }

    private void aplicar(MotivoBaja e, MotivoBajaRequest r) {
        e.setDescBaja(r.descBaja());
    }

    private void validarReferencias(MotivoBaja e) {
    }

    private MotivoBajaResponse toResponse(MotivoBaja e) {
        return new MotivoBajaResponse(
                e.getCodBaja(),
                e.getDescBaja()
        );
    }
}
