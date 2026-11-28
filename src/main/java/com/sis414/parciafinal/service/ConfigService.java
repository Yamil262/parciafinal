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
public class ConfigService {

    private final ConfigRepository configRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<ConfigResponse> listar(Pageable pageable) {
        Page<Config> page = configRepository.findAll(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public ConfigResponse obtener(Long id) {
        return toResponse(buscar(id));
    }

    private Config buscar(Long id) {
        return configRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró configuración con id " + id));
    }

    @Transactional
    public ConfigResponse crear(ConfigRequest request) {
        Config e = new Config();
        aplicar(e, request);
        validarReferencias(e);
        configRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public ConfigResponse actualizar(Long id, ConfigRequest request) {
        Config e = buscar(id);
        aplicar(e, request);
        validarReferencias(e);
        configRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(Long id) {
        Config e = buscar(id);
        configRepository.delete(e);
    }

    private void aplicar(Config e, ConfigRequest r) {
        e.setEntidad(r.entidad());
        e.setDescEnt(r.descEnt());
        e.setUnidad(r.unidad());
        e.setDescUni(r.descUni());
        e.setUsuario(r.usuario());
    }

    private void validarReferencias(Config e) {
    }

    private ConfigResponse toResponse(Config e) {
        return new ConfigResponse(
                e.getId(),
                e.getEntidad(),
                e.getDescEnt(),
                e.getUnidad(),
                e.getDescUni(),
                e.getUsuario()
        );
    }
}
