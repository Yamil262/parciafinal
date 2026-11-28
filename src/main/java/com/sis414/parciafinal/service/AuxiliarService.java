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
public class AuxiliarService {

    private final AuxiliarRepository auxiliarRepository;
    private final UnidadAdminRepository unidadAdminRepository;
    private final CodContRepository codContRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public AuxiliarService(AuxiliarRepository auxiliarRepository, UnidadAdminRepository unidadAdminRepository, CodContRepository codContRepository) {
        this.auxiliarRepository = auxiliarRepository;
        this.unidadAdminRepository = unidadAdminRepository;
        this.codContRepository = codContRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<AuxiliarResponse> listar(Pageable pageable) {
        Page<Auxiliar> page = auxiliarRepository.findAll(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public AuxiliarResponse obtener(String entidad, String unidad, Integer codCont, Integer codAux) {
        return toResponse(buscar(entidad, unidad, codCont, codAux));
    }

    private Auxiliar buscar(String entidad, String unidad, Integer codCont, Integer codAux) {
        return auxiliarRepository.findById(new AuxiliarId(entidad, unidad, codCont, codAux))
                .orElseThrow(() -> new NotFoundException("No se encontró auxiliar contable con clave [" + String.valueOf(entidad) + ", " + String.valueOf(unidad) + ", " + String.valueOf(codCont) + ", " + String.valueOf(codAux) + "]"));
    }

    @Transactional(readOnly = true)
    public List<AuxiliarResponse> listarPorCuenta(Integer codCont) {
        return auxiliarRepository.buscarPorCuenta(codCont).stream().map(this::toResponse).toList();
    }

    @Transactional
    public AuxiliarResponse crear(AuxiliarRequest request) {
        if (request.entidad() == null || request.entidad().isBlank()) {
            throw new BadRequestException("El campo 'entidad' es obligatorio para crear auxiliar contable");
        }
        if (request.unidad() == null || request.unidad().isBlank()) {
            throw new BadRequestException("El campo 'unidad' es obligatorio para crear auxiliar contable");
        }
        if (request.codCont() == null) {
            throw new BadRequestException("El campo 'codCont' es obligatorio para crear auxiliar contable");
        }
        if (request.codAux() == null) {
            throw new BadRequestException("El campo 'codAux' es obligatorio para crear auxiliar contable");
        }
        if (auxiliarRepository.existsById(new AuxiliarId(request.entidad(), request.unidad(), request.codCont(), request.codAux()))) {
            throw new ConflictException("Ya existe auxiliar contable con clave [" + String.valueOf(request.entidad()) + ", " + String.valueOf(request.unidad()) + ", " + String.valueOf(request.codCont()) + ", " + String.valueOf(request.codAux()) + "]");
        }
        Auxiliar e = new Auxiliar();
        e.setEntidad(request.entidad());
        e.setUnidad(request.unidad());
        e.setCodCont(request.codCont());
        e.setCodAux(request.codAux());
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), true);
        auxiliarRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public AuxiliarResponse actualizar(String entidad, String unidad, Integer codCont, Integer codAux, AuxiliarRequest request) {
        Auxiliar e = buscar(entidad, unidad, codCont, codAux);
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), false);
        auxiliarRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(String entidad, String unidad, Integer codCont, Integer codAux) {
        Auxiliar e = buscar(entidad, unidad, codCont, codAux);
        auxiliarRepository.delete(e);
    }

    private void aplicar(Auxiliar e, AuxiliarRequest r) {
        e.setNomAux(r.nomAux());
        e.setObserv(r.observ());
    }

    private void auditar(Auxiliar e, String usuar, boolean creacion) {
        String usuario = (usuar == null || usuar.isBlank()) ? "api" : usuar;
        LocalDate hoy = LocalDate.now();
        e.setFeult(hoy);
        e.setUsuar(usuario);
    }

    private void validarReferencias(Auxiliar e) {
        if (e.getEntidad() != null && !e.getEntidad().isBlank() && e.getUnidad() != null && !e.getUnidad().isBlank() && !unidadAdminRepository.existsById(new UnidadAdminId(e.getEntidad(), e.getUnidad()))) {
            throw new BadRequestException("No existe unidad administrativa con clave [" + String.valueOf(e.getEntidad()) + ", " + String.valueOf(e.getUnidad()) + "]");
        }
        if (e.getCodCont() != null && !codContRepository.existsById(e.getCodCont())) {
            throw new BadRequestException("No existe cuenta contable con clave [" + String.valueOf(e.getCodCont()) + "]");
        }
    }

    private AuxiliarResponse toResponse(Auxiliar e) {
        return new AuxiliarResponse(
                e.getEntidad(),
                e.getUnidad(),
                e.getCodCont(),
                e.getCodAux(),
                e.getNomAux(),
                e.getObserv(),
                e.getFeult(),
                e.getUsuar(),
                e.getUnidadAdmin() != null ? e.getUnidadAdmin().getDescrip() : null,
                e.getCuenta() != null ? e.getCuenta().getNombre() : null
        );
    }
}
