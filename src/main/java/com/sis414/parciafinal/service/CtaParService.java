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
public class CtaParService {

    private final CtaParRepository ctaParRepository;
    private final CodContRepository codContRepository;
    private final ObjGastoRepository objGastoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public CtaParService(CtaParRepository ctaParRepository, CodContRepository codContRepository, ObjGastoRepository objGastoRepository) {
        this.ctaParRepository = ctaParRepository;
        this.codContRepository = codContRepository;
        this.objGastoRepository = objGastoRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<CtaParResponse> listar(Pageable pageable) {
        Page<CtaPar> page = ctaParRepository.findAll(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public CtaParResponse obtener(Integer codCont, String partida, Integer gestion) {
        return toResponse(buscar(codCont, partida, gestion));
    }

    private CtaPar buscar(Integer codCont, String partida, Integer gestion) {
        return ctaParRepository.findById(new CtaParId(codCont, partida, gestion))
                .orElseThrow(() -> new NotFoundException("No se encontró relación cuenta-partida con clave [" + String.valueOf(codCont) + ", " + String.valueOf(partida) + ", " + String.valueOf(gestion) + "]"));
    }

    @Transactional
    public CtaParResponse crear(CtaParRequest request) {
        if (request.codCont() == null) {
            throw new BadRequestException("El campo 'codCont' es obligatorio para crear relación cuenta-partida");
        }
        if (request.partida() == null || request.partida().isBlank()) {
            throw new BadRequestException("El campo 'partida' es obligatorio para crear relación cuenta-partida");
        }
        if (request.gestion() == null) {
            throw new BadRequestException("El campo 'gestion' es obligatorio para crear relación cuenta-partida");
        }
        if (ctaParRepository.existsById(new CtaParId(request.codCont(), request.partida(), request.gestion()))) {
            throw new ConflictException("Ya existe relación cuenta-partida con clave [" + String.valueOf(request.codCont()) + ", " + String.valueOf(request.partida()) + ", " + String.valueOf(request.gestion()) + "]");
        }
        CtaPar e = new CtaPar();
        e.setCodCont(request.codCont());
        e.setPartida(request.partida());
        e.setGestion(request.gestion());
        aplicar(e, request);
        validarReferencias(e);
        ctaParRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public CtaParResponse actualizar(Integer codCont, String partida, Integer gestion, CtaParRequest request) {
        CtaPar e = buscar(codCont, partida, gestion);
        aplicar(e, request);
        validarReferencias(e);
        ctaParRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(Integer codCont, String partida, Integer gestion) {
        CtaPar e = buscar(codCont, partida, gestion);
        ctaParRepository.delete(e);
    }

    private void aplicar(CtaPar e, CtaParRequest r) {
    }

    private void validarReferencias(CtaPar e) {
        if (e.getCodCont() != null && !codContRepository.existsById(e.getCodCont())) {
            throw new BadRequestException("No existe cuenta contable con clave [" + String.valueOf(e.getCodCont()) + "]");
        }
        if (e.getGestion() != null && e.getPartida() != null && !e.getPartida().isBlank() && !objGastoRepository.existsById(new ObjGastoId(e.getGestion(), e.getPartida()))) {
            throw new BadRequestException("No existe partida de gasto con clave [" + String.valueOf(e.getGestion()) + ", " + String.valueOf(e.getPartida()) + "]");
        }
    }

    private CtaParResponse toResponse(CtaPar e) {
        return new CtaParResponse(
                e.getCodCont(),
                e.getPartida(),
                e.getGestion(),
                e.getCuenta() != null ? e.getCuenta().getNombre() : null,
                e.getObjetoGasto() != null ? e.getObjetoGasto().getDescrip() : null
        );
    }
}
