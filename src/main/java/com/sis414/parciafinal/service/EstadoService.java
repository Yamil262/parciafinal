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
public class EstadoService {

    private final EstadoRepository estadoRepository;
    private final ActualRepository actualRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EstadoService(EstadoRepository estadoRepository, ActualRepository actualRepository) {
        this.estadoRepository = estadoRepository;
        this.actualRepository = actualRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<EstadoResponse> listar(Pageable pageable) {
        Page<Estado> page = estadoRepository.findAll(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public EstadoResponse obtener(Integer codEstado) {
        return toResponse(buscar(codEstado));
    }

    private Estado buscar(Integer codEstado) {
        return estadoRepository.findById(codEstado)
                .orElseThrow(() -> new NotFoundException("No se encontró estado del bien con clave [" + String.valueOf(codEstado) + "]"));
    }

    @Transactional
    public EstadoResponse crear(EstadoRequest request) {
        if (request.codEstado() == null) {
            throw new BadRequestException("El campo 'codEstado' es obligatorio para crear estado del bien");
        }
        if (estadoRepository.existsById(request.codEstado())) {
            throw new ConflictException("Ya existe estado del bien con clave [" + String.valueOf(request.codEstado()) + "]");
        }
        Estado e = new Estado();
        e.setCodEstado(request.codEstado());
        aplicar(e, request);
        validarReferencias(e);
        estadoRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public EstadoResponse actualizar(Integer codEstado, EstadoRequest request) {
        Estado e = buscar(codEstado);
        aplicar(e, request);
        validarReferencias(e);
        estadoRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(Integer codEstado) {
        Estado e = buscar(codEstado);
        if (actualRepository.existsByCodEstado(e.getCodEstado())) {
            throw new ConflictException("No se puede eliminar estado del bien: tiene activos fijos asociados");
        }
        estadoRepository.delete(e);
    }

    private void aplicar(Estado e, EstadoRequest r) {
        e.setNomEstado(r.nomEstado());
    }

    private void validarReferencias(Estado e) {
    }

    private EstadoResponse toResponse(Estado e) {
        return new EstadoResponse(
                e.getCodEstado(),
                e.getNomEstado()
        );
    }
}
