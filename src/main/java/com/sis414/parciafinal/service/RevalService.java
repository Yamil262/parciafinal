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
public class RevalService {

    private final RevalRepository revalRepository;
    private final ActualRepository actualRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public RevalService(RevalRepository revalRepository, ActualRepository actualRepository) {
        this.revalRepository = revalRepository;
        this.actualRepository = actualRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<RevalResponse> listar(Pageable pageable) {
        Page<Reval> page = revalRepository.findAll(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public RevalResponse obtener(Long id) {
        return toResponse(buscar(id));
    }

    private Reval buscar(Long id) {
        return revalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró revaluación con id " + id));
    }

    @Transactional(readOnly = true)
    public List<RevalResponse> listarPorActivo(String entidad, String unidad, String codigo) {
        return revalRepository.buscarPorActivo(entidad, unidad, codigo).stream().map(this::toResponse).toList();
    }

    @Transactional
    public RevalResponse crear(RevalRequest request) {
        Reval e = new Reval();
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), true);
        revalRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public RevalResponse actualizar(Long id, RevalRequest request) {
        Reval e = buscar(id);
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), false);
        revalRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(Long id) {
        Reval e = buscar(id);
        revalRepository.delete(e);
    }

    private void aplicar(Reval e, RevalRequest r) {
        e.setEntidad(r.entidad());
        e.setUnidad(r.unidad());
        e.setCodigo(r.codigo());
        e.setCosto(r.costo());
        e.setDepAcuAnt(r.depAcuAnt());
        e.setDReval(r.dReval());
        e.setDia(r.dia());
        e.setMReval(r.mReval());
        e.setMes(r.mes());
        e.setAReval(r.aReval());
        e.setAno(r.ano());
        e.setVidaUtil(r.vidaUtil());
        e.setResolucion(r.resolucion());
        e.setObserv(r.observ());
        e.setDepAcu(r.depAcu());
        e.setActua(r.actua());
        e.setDepGestion(r.depGestion());
    }

    private void auditar(Reval e, String usuar, boolean creacion) {
        String usuario = (usuar == null || usuar.isBlank()) ? "api" : usuar;
        LocalDate hoy = LocalDate.now();
        e.setFeult(hoy);
        e.setUsuar(usuario);
        if (!creacion) {
            e.setFecMod(hoy);
            e.setUsuMod(usuario);
        }
    }

    private void validarReferencias(Reval e) {
        if (e.getEntidad() != null && !e.getEntidad().isBlank() && e.getUnidad() != null && !e.getUnidad().isBlank() && e.getCodigo() != null && !e.getCodigo().isBlank() && !actualRepository.existsById(new ActualId(e.getEntidad(), e.getUnidad(), e.getCodigo()))) {
            throw new BadRequestException("No existe activo fijo con clave [" + String.valueOf(e.getEntidad()) + ", " + String.valueOf(e.getUnidad()) + ", " + String.valueOf(e.getCodigo()) + "]");
        }
    }

    private RevalResponse toResponse(Reval e) {
        return new RevalResponse(
                e.getId(),
                e.getEntidad(),
                e.getUnidad(),
                e.getCodigo(),
                e.getCosto(),
                e.getDepAcuAnt(),
                e.getDReval(),
                e.getDia(),
                e.getMReval(),
                e.getMes(),
                e.getAReval(),
                e.getAno(),
                e.getVidaUtil(),
                e.getResolucion(),
                e.getObserv(),
                e.getUsuar(),
                e.getFeult(),
                e.getDepAcu(),
                e.getActua(),
                e.getDepGestion(),
                e.getFecMod(),
                e.getUsuMod(),
                e.getActivo() != null ? e.getActivo().getDescrip() : null
        );
    }
}
