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
public class MesService {

    private final MesRepository mesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public MesService(MesRepository mesRepository) {
        this.mesRepository = mesRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<MesResponse> listar(Pageable pageable) {
        Page<Mes> page = mesRepository.findAll(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public MesResponse obtener(Integer mes) {
        return toResponse(buscar(mes));
    }

    private Mes buscar(Integer mes) {
        return mesRepository.findById(mes)
                .orElseThrow(() -> new NotFoundException("No se encontró mes con clave [" + String.valueOf(mes) + "]"));
    }

    @Transactional
    public MesResponse crear(MesRequest request) {
        if (request.mes() == null) {
            throw new BadRequestException("El campo 'mes' es obligatorio para crear mes");
        }
        if (mesRepository.existsById(request.mes())) {
            throw new ConflictException("Ya existe mes con clave [" + String.valueOf(request.mes()) + "]");
        }
        Mes e = new Mes();
        e.setMes(request.mes());
        aplicar(e, request);
        validarReferencias(e);
        mesRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public MesResponse actualizar(Integer mes, MesRequest request) {
        Mes e = buscar(mes);
        aplicar(e, request);
        validarReferencias(e);
        mesRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(Integer mes) {
        Mes e = buscar(mes);
        mesRepository.delete(e);
    }

    private void aplicar(Mes e, MesRequest r) {
        e.setNomMes(r.nomMes());
    }

    private void validarReferencias(Mes e) {
    }

    private MesResponse toResponse(Mes e) {
        return new MesResponse(
                e.getMes(),
                e.getNomMes()
        );
    }
}
