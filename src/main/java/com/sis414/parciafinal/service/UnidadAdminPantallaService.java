package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.UnidadSeleccionadaDto;
import com.sis414.parciafinal.entity.UnidadAdmin;
import com.sis414.parciafinal.repository.UnidadAdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/** Pantalla ADMINISTRACION UNIDAD ADMINISTRATIVA: boton Seleccionar. */
@Service
public class UnidadAdminPantallaService {

    private final UnidadAdminRepository repository;
    private final ConfiguracionService configuracion;

    public UnidadAdminPantallaService(UnidadAdminRepository repository, ConfiguracionService configuracion) {
        this.repository = repository;
        this.configuracion = configuracion;
    }

    @Transactional
    public UnidadSeleccionadaDto seleccionar(Long id) {
        UnidadAdmin u = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidad no encontrada: " + id));
        configuracion.set(ConfiguracionService.UNIDAD, String.valueOf(id));
        return toDto(u);
    }

    @Transactional(readOnly = true)
    public UnidadSeleccionadaDto seleccionada() {
        Long id = configuracion.unidadSeleccionadaId();
        if (id == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay unidad seleccionada");
        return repository.findById(id).map(this::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay unidad seleccionada"));
    }

    private UnidadSeleccionadaDto toDto(UnidadAdmin u) {
        return new UnidadSeleccionadaDto(u.getId(), u.getUnidad(), u.getDescrip(), u.getCiudad(),
                u.getEntidad() == null ? null : u.getEntidad().getCodigo());
    }
}
