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
public class TrasfeService {

    private final TrasfeRepository trasfeRepository;
    private final ActualRepository actualRepository;
    private final OficinaRepository oficinaRepository;
    private final RespRepository respRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public TrasfeService(TrasfeRepository trasfeRepository, ActualRepository actualRepository, OficinaRepository oficinaRepository, RespRepository respRepository) {
        this.trasfeRepository = trasfeRepository;
        this.actualRepository = actualRepository;
        this.oficinaRepository = oficinaRepository;
        this.respRepository = respRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<TrasfeResponse> listar(Pageable pageable) {
        Page<Trasfe> page = trasfeRepository.findAll(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public TrasfeResponse obtener(Long id) {
        return toResponse(buscar(id));
    }

    private Trasfe buscar(Long id) {
        return trasfeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró transferencia con id " + id));
    }

    @Transactional(readOnly = true)
    public List<TrasfeResponse> listarPorActivo(String entidad, String unidad, String codigo) {
        return trasfeRepository.buscarPorActivo(entidad, unidad, codigo).stream().map(this::toResponse).toList();
    }

    @Transactional
    public TrasfeResponse crear(TrasfeRequest request) {
        Trasfe e = new Trasfe();
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), true);
        trasfeRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public TrasfeResponse actualizar(Long id, TrasfeRequest request) {
        Trasfe e = buscar(id);
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), false);
        trasfeRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(Long id) {
        Trasfe e = buscar(id);
        trasfeRepository.delete(e);
    }

    private void aplicar(Trasfe e, TrasfeRequest r) {
        e.setEntidad(r.entidad());
        e.setUnidad(r.unidad());
        e.setCodigo(r.codigo());
        e.setCodOfic(r.codOfic());
        e.setCodResp(r.codResp());
        e.setUnidadIni(r.unidadIni());
    }

    private void auditar(Trasfe e, String usuar, boolean creacion) {
        String usuario = (usuar == null || usuar.isBlank()) ? "api" : usuar;
        LocalDate hoy = LocalDate.now();
        e.setFeult(hoy);
        e.setUsuar(usuario);
    }

    private void validarReferencias(Trasfe e) {
        if (e.getEntidad() != null && !e.getEntidad().isBlank() && e.getUnidad() != null && !e.getUnidad().isBlank() && e.getCodigo() != null && !e.getCodigo().isBlank() && !actualRepository.existsById(new ActualId(e.getEntidad(), e.getUnidad(), e.getCodigo()))) {
            throw new BadRequestException("No existe activo fijo con clave [" + String.valueOf(e.getEntidad()) + ", " + String.valueOf(e.getUnidad()) + ", " + String.valueOf(e.getCodigo()) + "]");
        }
        if (e.getEntidad() != null && !e.getEntidad().isBlank() && e.getUnidad() != null && !e.getUnidad().isBlank() && e.getCodOfic() != null && !oficinaRepository.existsById(new OficinaId(e.getEntidad(), e.getUnidad(), e.getCodOfic()))) {
            throw new BadRequestException("No existe oficina con clave [" + String.valueOf(e.getEntidad()) + ", " + String.valueOf(e.getUnidad()) + ", " + String.valueOf(e.getCodOfic()) + "]");
        }
        if (e.getEntidad() != null && !e.getEntidad().isBlank() && e.getUnidad() != null && !e.getUnidad().isBlank() && e.getCodOfic() != null && e.getCodResp() != null && !respRepository.existsById(new RespId(e.getEntidad(), e.getUnidad(), e.getCodOfic(), e.getCodResp()))) {
            throw new BadRequestException("No existe responsable con clave [" + String.valueOf(e.getEntidad()) + ", " + String.valueOf(e.getUnidad()) + ", " + String.valueOf(e.getCodOfic()) + ", " + String.valueOf(e.getCodResp()) + "]");
        }
    }

    private TrasfeResponse toResponse(Trasfe e) {
        return new TrasfeResponse(
                e.getId(),
                e.getEntidad(),
                e.getUnidad(),
                e.getCodigo(),
                e.getCodOfic(),
                e.getCodResp(),
                e.getFeult(),
                e.getUsuar(),
                e.getUnidadIni(),
                e.getActivo() != null ? e.getActivo().getDescrip() : null,
                e.getOficina() != null ? e.getOficina().getNomOfic() : null,
                e.getResponsable() != null ? e.getResponsable().getNomResp() : null
        );
    }
}
