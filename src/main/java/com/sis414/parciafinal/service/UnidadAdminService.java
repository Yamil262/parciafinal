
package com.sis414.parciafinal.service;

import com.sis414.parciafinal.common.PageResponse;
import com.sis414.parciafinal.dto.*;
import com.sis414.parciafinal.entity.*;
import com.sis414.parciafinal.exception.*;
import com.sis414.parciafinal.repository.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UnidadAdminService {

    private final UnidadAdminRepository unidadAdminRepository;
    private final OficinaRepository oficinaRepository;
    private final AuxiliarRepository auxiliarRepository;

    public UnidadAdminService(
            UnidadAdminRepository unidadAdminRepository,
            OficinaRepository oficinaRepository,
            AuxiliarRepository auxiliarRepository) {

        this.unidadAdminRepository = unidadAdminRepository;
        this.oficinaRepository = oficinaRepository;
        this.auxiliarRepository = auxiliarRepository;
    }

    // LISTAR UNIDADES ADMINISTRATIVAS
    @Transactional(readOnly = true)
    public PageResponse<UnidadAdminResponse> listar(Pageable pageable) {

        Page<UnidadAdmin> page =
                unidadAdminRepository.findAll(pageable);

        return PageResponse.of(page.map(this::toResponse));
    }

    // OBTENER UNIDAD ADMINISTRATIVA
    @Transactional(readOnly = true)
    public UnidadAdminResponse obtener(String entidad, String unidad) {

        return toResponse(buscar(entidad, unidad));
    }

    // BUSCAR POR CLAVE PRIMARIA
    private UnidadAdmin buscar(String entidad, String unidad) {

        return unidadAdminRepository.findById(
                new UnidadAdminId(entidad, unidad)
        ).orElseThrow(() ->
                new NotFoundException(
                        "No se encontró unidad administrativa con clave ["
                                + entidad + ", " + unidad + "]"
                )
        );
    }

    // CREAR UNIDAD ADMINISTRATIVA
    @Transactional
    public UnidadAdminResponse crear(UnidadAdminRequest request) {

        if (request == null) {
            throw new BadRequestException(
                    "Los datos de la unidad administrativa son obligatorios"
            );
        }

        if (request.entidad() == null ||
                request.entidad().isBlank()) {

            throw new BadRequestException(
                    "El campo entidad es obligatorio"
            );
        }

        if (request.unidad() == null ||
                request.unidad().isBlank()) {

            throw new BadRequestException(
                    "El campo unidad es obligatorio"
            );
        }

        UnidadAdminId id = new UnidadAdminId(
                request.entidad(),
                request.unidad()
        );

        if (unidadAdminRepository.existsById(id)) {

            throw new ConflictException(
                    "Ya existe unidad administrativa con clave ["
                            + request.entidad() + ", "
                            + request.unidad() + "]"
            );
        }

        UnidadAdmin e = new UnidadAdmin();

        e.setEntidad(request.entidad());
        e.setUnidad(request.unidad());

        aplicar(e, request);

        validarReferencias(e);

        UnidadAdmin guardada =
                unidadAdminRepository.saveAndFlush(e);

        return toResponse(guardada);
    }

    // ACTUALIZAR UNIDAD ADMINISTRATIVA
    @Transactional
    public UnidadAdminResponse actualizar(
            String entidad,
            String unidad,
            UnidadAdminRequest request) {

        if (request == null) {
            throw new BadRequestException(
                    "Los datos de actualización son obligatorios"
            );
        }

        UnidadAdmin e = buscar(entidad, unidad);

        aplicar(e, request);

        validarReferencias(e);

        UnidadAdmin guardada =
                unidadAdminRepository.saveAndFlush(e);

        return toResponse(guardada);
    }

    // ELIMINAR UNIDAD ADMINISTRATIVA
    @Transactional
    public void eliminar(String entidad, String unidad) {

        UnidadAdmin e = buscar(entidad, unidad);

        if (oficinaRepository.existsByEntidadAndUnidad(
                e.getEntidad(), e.getUnidad())) {

            throw new ConflictException(
                    "No se puede eliminar la unidad administrativa: "
                            + "tiene oficinas asociadas"
            );
        }

        if (auxiliarRepository.existsByEntidadAndUnidad(
                e.getEntidad(), e.getUnidad())) {

            throw new ConflictException(
                    "No se puede eliminar la unidad administrativa: "
                            + "tiene auxiliares contables asociados"
            );
        }

        unidadAdminRepository.delete(e);
    }

    // APLICAR DATOS
    private void aplicar(UnidadAdmin e, UnidadAdminRequest r) {

        e.setDescrip(r.descrip());
        e.setCiudad(r.ciudad());
        e.setEstadoUni(r.estadoUni());
    }

    // VALIDAR REFERENCIAS
    private void validarReferencias(UnidadAdmin e) {

        // Agregar validaciones de claves foráneas si corresponde.
    }

    // CONVERTIR ENTIDAD A RESPONSE
    private UnidadAdminResponse toResponse(UnidadAdmin e) {

        return new UnidadAdminResponse(
                e.getEntidad(),
                e.getUnidad(),
                e.getDescrip(),
                e.getCiudad(),
                e.getEstadoUni()
        );
    }
}
