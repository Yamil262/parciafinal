package com.sis414.parciafinal.service;

import com.sis414.parciafinal.common.DataMasking;
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
public class RespService {

    private final RespRepository respRepository;
    private final OficinaRepository oficinaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public RespService(RespRepository respRepository, OficinaRepository oficinaRepository) {
        this.respRepository = respRepository;
        this.oficinaRepository = oficinaRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<RespResponse> listar(boolean incluirAnulados, Pageable pageable) {
        Page<Resp> page = incluirAnulados ? respRepository.findAll(pageable) : respRepository.listarActivos(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public RespResponse obtener(String entidad, String unidad, Integer codOfic, Integer codResp) {
        return toResponse(buscar(entidad, unidad, codOfic, codResp));
    }

    private Resp buscar(String entidad, String unidad, Integer codOfic, Integer codResp) {
        return respRepository.findById(new RespId(entidad, unidad, codOfic, codResp))
                .orElseThrow(() -> new NotFoundException("No se encontró responsable con clave [" + String.valueOf(entidad) + ", " + String.valueOf(unidad) + ", " + String.valueOf(codOfic) + ", " + String.valueOf(codResp) + "]"));
    }

    @Transactional(readOnly = true)
    public List<RespResponse> listarPorOficina(String entidad, String unidad, Integer codOfic) {
        return respRepository.buscarPorOficina(entidad, unidad, codOfic).stream().map(this::toResponse).toList();
    }

    @Transactional
    public RespResponse crear(RespRequest request) {
        if (request.entidad() == null || request.entidad().isBlank()) {
            throw new BadRequestException("El campo 'entidad' es obligatorio para crear responsable");
        }
        if (request.unidad() == null || request.unidad().isBlank()) {
            throw new BadRequestException("El campo 'unidad' es obligatorio para crear responsable");
        }
        if (request.codOfic() == null) {
            throw new BadRequestException("El campo 'codOfic' es obligatorio para crear responsable");
        }
        if (request.codResp() == null) {
            throw new BadRequestException("El campo 'codResp' es obligatorio para crear responsable");
        }
        if (respRepository.existsById(new RespId(request.entidad(), request.unidad(), request.codOfic(), request.codResp()))) {
            throw new ConflictException("Ya existe responsable con clave [" + String.valueOf(request.entidad()) + ", " + String.valueOf(request.unidad()) + ", " + String.valueOf(request.codOfic()) + ", " + String.valueOf(request.codResp()) + "]");
        }
        Resp e = new Resp();
        e.setEntidad(request.entidad());
        e.setUnidad(request.unidad());
        e.setCodOfic(request.codOfic());
        e.setCodResp(request.codResp());
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), true);
        e.setApiEstado(1);
        respRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public RespResponse actualizar(String entidad, String unidad, Integer codOfic, Integer codResp, RespRequest request) {
        Resp e = buscar(entidad, unidad, codOfic, codResp);
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), false);
        respRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(String entidad, String unidad, Integer codOfic, Integer codResp) {
        Resp e = buscar(entidad, unidad, codOfic, codResp);
        // Baja lógica: api_estado = 3 (anulado), igual que el sistema original
        e.setApiEstado(3);
        auditar(e, null, false);
        respRepository.save(e);
    }

    private void aplicar(Resp e, RespRequest r) {
        e.setNomResp(r.nomResp());
        e.setCargo(r.cargo());
        e.setObserv(r.observ());
        e.setCi(r.ci());
        e.setCodExp(r.codExp());
    }

    private void auditar(Resp e, String usuar, boolean creacion) {
        String usuario = (usuar == null || usuar.isBlank()) ? "api" : usuar;
        LocalDate hoy = LocalDate.now();
        e.setFeult(hoy);
        e.setUsuar(usuario);
    }

    private void validarReferencias(Resp e) {
        if (e.getEntidad() != null && !e.getEntidad().isBlank() && e.getUnidad() != null && !e.getUnidad().isBlank() && e.getCodOfic() != null && !oficinaRepository.existsById(new OficinaId(e.getEntidad(), e.getUnidad(), e.getCodOfic()))) {
            throw new BadRequestException("No existe oficina con clave [" + String.valueOf(e.getEntidad()) + ", " + String.valueOf(e.getUnidad()) + ", " + String.valueOf(e.getCodOfic()) + "]");
        }
    }

    private RespResponse toResponse(Resp e) {
        return new RespResponse(
                e.getEntidad(),
                e.getUnidad(),
                e.getCodOfic(),
                e.getCodResp(),
                e.getNomResp(),
                e.getCargo(),
                e.getObserv(),
                DataMasking.mask(e.getCi()),
                e.getFeult(),
                e.getUsuar(),
                e.getCodExp(),
                e.getApiEstado(),
                e.getOficina() != null ? e.getOficina().getNomOfic() : null
        );
    }
}
