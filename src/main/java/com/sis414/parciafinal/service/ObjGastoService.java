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
public class ObjGastoService {

    private final ObjGastoRepository objGastoRepository;
    private final CtaParRepository ctaParRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ObjGastoService(ObjGastoRepository objGastoRepository, CtaParRepository ctaParRepository) {
        this.objGastoRepository = objGastoRepository;
        this.ctaParRepository = ctaParRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<ObjGastoResponse> listar(Pageable pageable) {
        Page<ObjGasto> page = objGastoRepository.findAll(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public ObjGastoResponse obtener(Integer gestion, String partida) {
        return toResponse(buscar(gestion, partida));
    }

    private ObjGasto buscar(Integer gestion, String partida) {
        return objGastoRepository.findById(new ObjGastoId(gestion, partida))
                .orElseThrow(() -> new NotFoundException("No se encontró partida de gasto con clave [" + String.valueOf(gestion) + ", " + String.valueOf(partida) + "]"));
    }

    @Transactional
    public ObjGastoResponse crear(ObjGastoRequest request) {
        if (request.gestion() == null) {
            throw new BadRequestException("El campo 'gestion' es obligatorio para crear partida de gasto");
        }
        if (request.partida() == null || request.partida().isBlank()) {
            throw new BadRequestException("El campo 'partida' es obligatorio para crear partida de gasto");
        }
        if (objGastoRepository.existsById(new ObjGastoId(request.gestion(), request.partida()))) {
            throw new ConflictException("Ya existe partida de gasto con clave [" + String.valueOf(request.gestion()) + ", " + String.valueOf(request.partida()) + "]");
        }
        ObjGasto e = new ObjGasto();
        e.setGestion(request.gestion());
        e.setPartida(request.partida());
        aplicar(e, request);
        validarReferencias(e);
        objGastoRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public ObjGastoResponse actualizar(Integer gestion, String partida, ObjGastoRequest request) {
        ObjGasto e = buscar(gestion, partida);
        aplicar(e, request);
        validarReferencias(e);
        objGastoRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(Integer gestion, String partida) {
        ObjGasto e = buscar(gestion, partida);
        if (ctaParRepository.existsByGestionAndPartida(e.getGestion(), e.getPartida())) {
            throw new ConflictException("No se puede eliminar partida de gasto: tiene relaciones cuenta-partida asociados");
        }
        objGastoRepository.delete(e);
    }

    private void aplicar(ObjGasto e, ObjGastoRequest r) {
        e.setDescrip(r.descrip());
    }

    private void validarReferencias(ObjGasto e) {
    }

    private ObjGastoResponse toResponse(ObjGasto e) {
        return new ObjGastoResponse(
                e.getGestion(),
                e.getPartida(),
                e.getDescrip()
        );
    }
}
