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
public class ClaDeptsService {

    private final ClaDeptsRepository claDeptsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ClaDeptsService(ClaDeptsRepository claDeptsRepository) {
        this.claDeptsRepository = claDeptsRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<ClaDeptsResponse> listar(Pageable pageable) {
        Page<ClaDepts> page = claDeptsRepository.findAll(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public ClaDeptsResponse obtener(Integer codigo) {
        return toResponse(buscar(codigo));
    }

    private ClaDepts buscar(Integer codigo) {
        return claDeptsRepository.findById(codigo)
                .orElseThrow(() -> new NotFoundException("No se encontró departamento con clave [" + String.valueOf(codigo) + "]"));
    }

    @Transactional
    public ClaDeptsResponse crear(ClaDeptsRequest request) {
        if (request.codigo() == null) {
            throw new BadRequestException("El campo 'codigo' es obligatorio para crear departamento");
        }
        if (claDeptsRepository.existsById(request.codigo())) {
            throw new ConflictException("Ya existe departamento con clave [" + String.valueOf(request.codigo()) + "]");
        }
        ClaDepts e = new ClaDepts();
        e.setCodigo(request.codigo());
        aplicar(e, request);
        validarReferencias(e);
        claDeptsRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public ClaDeptsResponse actualizar(Integer codigo, ClaDeptsRequest request) {
        ClaDepts e = buscar(codigo);
        aplicar(e, request);
        validarReferencias(e);
        claDeptsRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(Integer codigo) {
        ClaDepts e = buscar(codigo);
        claDeptsRepository.delete(e);
    }

    private void aplicar(ClaDepts e, ClaDeptsRequest r) {
        e.setDescripcion(r.descripcion());
        e.setSigla(r.sigla());
    }

    private void validarReferencias(ClaDepts e) {
    }

    private ClaDeptsResponse toResponse(ClaDepts e) {
        return new ClaDeptsResponse(
                e.getCodigo(),
                e.getDescripcion(),
                e.getSigla()
        );
    }
}
