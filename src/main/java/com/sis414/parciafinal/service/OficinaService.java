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
public class OficinaService {

    private final OficinaRepository oficinaRepository;
    private final UnidadAdminRepository unidadAdminRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public OficinaService(OficinaRepository oficinaRepository, UnidadAdminRepository unidadAdminRepository) {
        this.oficinaRepository = oficinaRepository;
        this.unidadAdminRepository = unidadAdminRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<OficinaResponse> listar(boolean incluirAnulados, Pageable pageable) {
        Page<Oficina> page = incluirAnulados ? oficinaRepository.findAll(pageable) : oficinaRepository.listarActivos(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public OficinaResponse obtener(String entidad, String unidad, Integer codOfic) {
        return toResponse(buscar(entidad, unidad, codOfic));
    }

    private Oficina buscar(String entidad, String unidad, Integer codOfic) {
        return oficinaRepository.findById(new OficinaId(entidad, unidad, codOfic))
                .orElseThrow(() -> new NotFoundException("No se encontró oficina con clave [" + String.valueOf(entidad) + ", " + String.valueOf(unidad) + ", " + String.valueOf(codOfic) + "]"));
    }

    @Transactional(readOnly = true)
    public List<OficinaResponse> listarPorUnidad(String entidad, String unidad) {
        return oficinaRepository.buscarPorUnidad(entidad, unidad).stream().map(this::toResponse).toList();
    }

    @Transactional
    public OficinaResponse crear(OficinaRequest request) {
        if (request.entidad() == null || request.entidad().isBlank()) {
            throw new BadRequestException("El campo 'entidad' es obligatorio para crear oficina");
        }
        if (request.unidad() == null || request.unidad().isBlank()) {
            throw new BadRequestException("El campo 'unidad' es obligatorio para crear oficina");
        }
        if (request.codOfic() == null) {
            throw new BadRequestException("El campo 'codOfic' es obligatorio para crear oficina");
        }
        if (oficinaRepository.existsById(new OficinaId(request.entidad(), request.unidad(), request.codOfic()))) {
            throw new ConflictException("Ya existe oficina con clave [" + String.valueOf(request.entidad()) + ", " + String.valueOf(request.unidad()) + ", " + String.valueOf(request.codOfic()) + "]");
        }
        Oficina e = new Oficina();
        e.setEntidad(request.entidad());
        e.setUnidad(request.unidad());
        e.setCodOfic(request.codOfic());
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), true);
        e.setApiEstado(1);
        oficinaRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public OficinaResponse actualizar(String entidad, String unidad, Integer codOfic, OficinaRequest request) {
        Oficina e = buscar(entidad, unidad, codOfic);
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), false);
        oficinaRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(String entidad, String unidad, Integer codOfic) {
        Oficina e = buscar(entidad, unidad, codOfic);
        // Baja lógica: api_estado = 3 (anulado), igual que el sistema original
        e.setApiEstado(3);
        auditar(e, null, false);
        oficinaRepository.save(e);
    }

    private void aplicar(Oficina e, OficinaRequest r) {
        e.setNomOfic(r.nomOfic());
        e.setObserv(r.observ());
    }

    private void auditar(Oficina e, String usuar, boolean creacion) {
        String usuario = (usuar == null || usuar.isBlank()) ? "api" : usuar;
        LocalDate hoy = LocalDate.now();
        e.setFeult(hoy);
        e.setUsuar(usuario);
    }

    private void validarReferencias(Oficina e) {
        if (e.getEntidad() != null && !e.getEntidad().isBlank() && e.getUnidad() != null && !e.getUnidad().isBlank() && !unidadAdminRepository.existsById(new UnidadAdminId(e.getEntidad(), e.getUnidad()))) {
            throw new BadRequestException("No existe unidad administrativa con clave [" + String.valueOf(e.getEntidad()) + ", " + String.valueOf(e.getUnidad()) + "]");
        }
    }

    private OficinaResponse toResponse(Oficina e) {
        return new OficinaResponse(
                e.getEntidad(),
                e.getUnidad(),
                e.getCodOfic(),
                e.getNomOfic(),
                e.getObserv(),
                e.getFeult(),
                e.getUsuar(),
                e.getApiEstado(),
                e.getUnidadAdmin() != null ? e.getUnidadAdmin().getDescrip() : null
        );
    }
}
