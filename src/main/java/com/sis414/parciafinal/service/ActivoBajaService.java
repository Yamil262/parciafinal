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
public class ActivoBajaService {

    private final ActivoBajaRepository activoBajaRepository;
    private final MotivoBajaRepository motivoBajaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ActivoBajaService(ActivoBajaRepository activoBajaRepository, MotivoBajaRepository motivoBajaRepository) {
        this.activoBajaRepository = activoBajaRepository;
        this.motivoBajaRepository = motivoBajaRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<ActivoBajaResponse> listar(Pageable pageable) {
        Page<ActivoBaja> page = activoBajaRepository.findAll(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public ActivoBajaResponse obtener(Long id) {
        return toResponse(buscar(id));
    }

    private ActivoBaja buscar(Long id) {
        return activoBajaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró activo dado de baja con id " + id));
    }

    @Transactional
    public ActivoBajaResponse crear(ActivoBajaRequest request) {
        ActivoBaja e = new ActivoBaja();
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), true);
        activoBajaRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public ActivoBajaResponse actualizar(Long id, ActivoBajaRequest request) {
        ActivoBaja e = buscar(id);
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), false);
        activoBajaRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(Long id) {
        ActivoBaja e = buscar(id);
        activoBajaRepository.delete(e);
    }

    private void aplicar(ActivoBaja e, ActivoBajaRequest r) {
        e.setEntidad(r.entidad());
        e.setUnidad(r.unidad());
        e.setCodigo(r.codigo());
        e.setCodBaja(r.codBaja());
        e.setCodCont(r.codCont());
        e.setCodAux(r.codAux());
        e.setCodOfic(r.codOfic());
        e.setCodResp(r.codResp());
        e.setCosto(r.costo());
        e.setDepAcuAnt(r.depAcuAnt());
        e.setDescrip(r.descrip());
        e.setDBaja(r.dBaja());
        e.setDia(r.dia());
        e.setMBaja(r.mBaja());
        e.setMes(r.mes());
        e.setABaja(r.aBaja());
        e.setAno(r.ano());
        e.setBRev(r.bRev());
        e.setVidaUtil(r.vidaUtil());
        e.setResolucion(r.resolucion());
        e.setObserv(r.observ());
        e.setMotivo(r.motivo());
        e.setDepAcu(r.depAcu());
        e.setActua(r.actua());
        e.setDepGestion(r.depGestion());
        e.setCosBajIni(r.cosBajIni());
        e.setCosDaIni(r.cosDaIni());
        e.setCosActBaj(r.cosActBaj());
        e.setActDaBaj(r.actDaBaj());
    }

    private void auditar(ActivoBaja e, String usuar, boolean creacion) {
        String usuario = (usuar == null || usuar.isBlank()) ? "api" : usuar;
        LocalDate hoy = LocalDate.now();
        e.setFeult(hoy);
        e.setUsuar(usuario);
        if (!creacion) {
            e.setFecMod(hoy);
            e.setUsuMod(usuario);
        }
    }

    private void validarReferencias(ActivoBaja e) {
        if (e.getCodBaja() != null && !motivoBajaRepository.existsById(e.getCodBaja())) {
            throw new BadRequestException("No existe motivo de baja con clave [" + String.valueOf(e.getCodBaja()) + "]");
        }
    }

    private ActivoBajaResponse toResponse(ActivoBaja e) {
        return new ActivoBajaResponse(
                e.getId(),
                e.getEntidad(),
                e.getUnidad(),
                e.getCodigo(),
                e.getCodBaja(),
                e.getCodCont(),
                e.getCodAux(),
                e.getCodOfic(),
                e.getCodResp(),
                e.getCosto(),
                e.getDepAcuAnt(),
                e.getDescrip(),
                e.getDBaja(),
                e.getDia(),
                e.getMBaja(),
                e.getMes(),
                e.getABaja(),
                e.getAno(),
                e.getBRev(),
                e.getVidaUtil(),
                e.getResolucion(),
                e.getObserv(),
                e.getMotivo(),
                e.getDepAcu(),
                e.getActua(),
                e.getDepGestion(),
                e.getCosBajIni(),
                e.getCosDaIni(),
                e.getCosActBaj(),
                e.getActDaBaj(),
                e.getUsuar(),
                e.getFeult(),
                e.getFecMod(),
                e.getUsuMod(),
                e.getMotivoBaja() != null ? e.getMotivoBaja().getDescBaja() : null
        );
    }
}
