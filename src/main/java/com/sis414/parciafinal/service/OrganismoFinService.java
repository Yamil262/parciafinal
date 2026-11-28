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
public class OrganismoFinService {

    private final OrganismoFinRepository organismoFinRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public OrganismoFinService(OrganismoFinRepository organismoFinRepository) {
        this.organismoFinRepository = organismoFinRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<OrganismoFinResponse> listar(Pageable pageable) {
        Page<OrganismoFin> page = organismoFinRepository.findAll(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public OrganismoFinResponse obtener(Integer gestion, String codOf) {
        return toResponse(buscar(gestion, codOf));
    }

    private OrganismoFin buscar(Integer gestion, String codOf) {
        return organismoFinRepository.findById(new OrganismoFinId(gestion, codOf))
                .orElseThrow(() -> new NotFoundException("No se encontró organismo financiador con clave [" + String.valueOf(gestion) + ", " + String.valueOf(codOf) + "]"));
    }

    @Transactional
    public OrganismoFinResponse crear(OrganismoFinRequest request) {
        if (request.gestion() == null) {
            throw new BadRequestException("El campo 'gestion' es obligatorio para crear organismo financiador");
        }
        if (request.codOf() == null || request.codOf().isBlank()) {
            throw new BadRequestException("El campo 'codOf' es obligatorio para crear organismo financiador");
        }
        if (organismoFinRepository.existsById(new OrganismoFinId(request.gestion(), request.codOf()))) {
            throw new ConflictException("Ya existe organismo financiador con clave [" + String.valueOf(request.gestion()) + ", " + String.valueOf(request.codOf()) + "]");
        }
        OrganismoFin e = new OrganismoFin();
        e.setGestion(request.gestion());
        e.setCodOf(request.codOf());
        aplicar(e, request);
        validarReferencias(e);
        organismoFinRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public OrganismoFinResponse actualizar(Integer gestion, String codOf, OrganismoFinRequest request) {
        OrganismoFin e = buscar(gestion, codOf);
        aplicar(e, request);
        validarReferencias(e);
        organismoFinRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(Integer gestion, String codOf) {
        OrganismoFin e = buscar(gestion, codOf);
        organismoFinRepository.delete(e);
    }

    private void aplicar(OrganismoFin e, OrganismoFinRequest r) {
        e.setDes(r.des());
        e.setSigla(r.sigla());
    }

    private void validarReferencias(OrganismoFin e) {
    }

    private OrganismoFinResponse toResponse(OrganismoFin e) {
        return new OrganismoFinResponse(
                e.getGestion(),
                e.getCodOf(),
                e.getDes(),
                e.getSigla()
        );
    }
}
