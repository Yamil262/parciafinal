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
public class CodContService {

    private final CodContRepository codContRepository;
    private final AuxiliarRepository auxiliarRepository;
    private final ActualRepository actualRepository;
    private final CtaParRepository ctaParRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public CodContService(CodContRepository codContRepository, AuxiliarRepository auxiliarRepository, ActualRepository actualRepository, CtaParRepository ctaParRepository) {
        this.codContRepository = codContRepository;
        this.auxiliarRepository = auxiliarRepository;
        this.actualRepository = actualRepository;
        this.ctaParRepository = ctaParRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<CodContResponse> listar(Pageable pageable) {
        Page<CodCont> page = codContRepository.findAll(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public CodContResponse obtener(Integer codCont) {
        return toResponse(buscar(codCont));
    }

    private CodCont buscar(Integer codCont) {
        return codContRepository.findById(codCont)
                .orElseThrow(() -> new NotFoundException("No se encontró cuenta contable con clave [" + String.valueOf(codCont) + "]"));
    }

    @Transactional
    public CodContResponse crear(CodContRequest request) {
        if (request.codCont() == null) {
            throw new BadRequestException("El campo 'codCont' es obligatorio para crear cuenta contable");
        }
        if (codContRepository.existsById(request.codCont())) {
            throw new ConflictException("Ya existe cuenta contable con clave [" + String.valueOf(request.codCont()) + "]");
        }
        CodCont e = new CodCont();
        e.setCodCont(request.codCont());
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), true);
        codContRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public CodContResponse actualizar(Integer codCont, CodContRequest request) {
        CodCont e = buscar(codCont);
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), false);
        codContRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(Integer codCont) {
        CodCont e = buscar(codCont);
        if (auxiliarRepository.existsByCodCont(e.getCodCont())) {
            throw new ConflictException("No se puede eliminar cuenta contable: tiene auxiliares contables asociados");
        }
        if (actualRepository.existsByCodCont(e.getCodCont())) {
            throw new ConflictException("No se puede eliminar cuenta contable: tiene activos fijos asociados");
        }
        if (ctaParRepository.existsByCodCont(e.getCodCont())) {
            throw new ConflictException("No se puede eliminar cuenta contable: tiene relaciones cuenta-partida asociados");
        }
        codContRepository.delete(e);
    }

    private void aplicar(CodCont e, CodContRequest r) {
        e.setNombre(r.nombre());
        e.setVidaUtil(r.vidaUtil());
        e.setObserv(r.observ());
        e.setDepreciar(r.depreciar());
        e.setActualizar(r.actualizar());
    }

    private void auditar(CodCont e, String usuar, boolean creacion) {
        String usuario = (usuar == null || usuar.isBlank()) ? "api" : usuar;
        LocalDate hoy = LocalDate.now();
        e.setFeult(hoy);
        e.setUsuar(usuario);
    }

    private void validarReferencias(CodCont e) {
    }

    private CodContResponse toResponse(CodCont e) {
        return new CodContResponse(
                e.getCodCont(),
                e.getNombre(),
                e.getVidaUtil(),
                e.getObserv(),
                e.getDepreciar(),
                e.getActualizar(),
                e.getFeult(),
                e.getUsuar()
        );
    }
}
