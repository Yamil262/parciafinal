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
public class ActualService {

    private final ActualRepository actualRepository;
    private final UnidadAdminRepository unidadAdminRepository;
    private final CodContRepository codContRepository;
    private final AuxiliarRepository auxiliarRepository;
    private final OficinaRepository oficinaRepository;
    private final RespRepository respRepository;
    private final EstadoRepository estadoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ActualService(ActualRepository actualRepository, UnidadAdminRepository unidadAdminRepository, CodContRepository codContRepository, AuxiliarRepository auxiliarRepository, OficinaRepository oficinaRepository, RespRepository respRepository, EstadoRepository estadoRepository) {
        this.actualRepository = actualRepository;
        this.unidadAdminRepository = unidadAdminRepository;
        this.codContRepository = codContRepository;
        this.auxiliarRepository = auxiliarRepository;
        this.oficinaRepository = oficinaRepository;
        this.respRepository = respRepository;
        this.estadoRepository = estadoRepository;
    }

    @Transactional(readOnly = true)
    public PageResponse<ActualResponse> listar(boolean incluirAnulados, Pageable pageable) {
        Page<Actual> page = incluirAnulados ? actualRepository.findAll(pageable) : actualRepository.listarActivos(pageable);
        return PageResponse.of(page.map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public ActualResponse obtener(String entidad, String unidad, String codigo) {
        return toResponse(buscar(entidad, unidad, codigo));
    }

    private Actual buscar(String entidad, String unidad, String codigo) {
        return actualRepository.findById(new ActualId(entidad, unidad, codigo))
                .orElseThrow(() -> new NotFoundException("No se encontró activo fijo con clave [" + String.valueOf(entidad) + ", " + String.valueOf(unidad) + ", " + String.valueOf(codigo) + "]"));
    }

    @Transactional(readOnly = true)
    public List<ActualResponse> listarPorUnidad(String entidad, String unidad) {
        return actualRepository.buscarPorUnidad(entidad, unidad).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<ActualResponse> listarPorOficina(String entidad, String unidad, Integer codOfic) {
        return actualRepository.buscarPorOficina(entidad, unidad, codOfic).stream().map(this::toResponse).toList();
    }

    @Transactional
    public ActualResponse crear(ActualRequest request) {
        if (request.entidad() == null || request.entidad().isBlank()) {
            throw new BadRequestException("El campo 'entidad' es obligatorio para crear activo fijo");
        }
        if (request.unidad() == null || request.unidad().isBlank()) {
            throw new BadRequestException("El campo 'unidad' es obligatorio para crear activo fijo");
        }
        if (request.codigo() == null || request.codigo().isBlank()) {
            throw new BadRequestException("El campo 'codigo' es obligatorio para crear activo fijo");
        }
        if (actualRepository.existsById(new ActualId(request.entidad(), request.unidad(), request.codigo()))) {
            throw new ConflictException("Ya existe activo fijo con clave [" + String.valueOf(request.entidad()) + ", " + String.valueOf(request.unidad()) + ", " + String.valueOf(request.codigo()) + "]");
        }
        Actual e = new Actual();
        e.setEntidad(request.entidad());
        e.setUnidad(request.unidad());
        e.setCodigo(request.codigo());
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), true);
        e.setApiEstado(1);
        actualRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public ActualResponse actualizar(String entidad, String unidad, String codigo, ActualRequest request) {
        Actual e = buscar(entidad, unidad, codigo);
        aplicar(e, request);
        validarReferencias(e);
        auditar(e, request.usuar(), false);
        actualRepository.saveAndFlush(e);
        entityManager.refresh(e);
        return toResponse(e);
    }

    @Transactional
    public void eliminar(String entidad, String unidad, String codigo) {
        Actual e = buscar(entidad, unidad, codigo);
        // Baja lógica: api_estado = 3 (anulado), igual que el sistema original
        e.setApiEstado(3);
        auditar(e, null, false);
        actualRepository.save(e);
    }

    private void aplicar(Actual e, ActualRequest r) {
        e.setCodCont(r.codCont());
        e.setCodAux(r.codAux());
        e.setVidaUtil(r.vidaUtil());
        e.setDescrip(r.descrip());
        e.setCosto(r.costo());
        e.setDepAcu(r.depAcu());
        e.setMes(r.mes());
        e.setAno(r.ano());
        e.setBRev(r.bRev());
        e.setDia(r.dia());
        e.setCodOfic(r.codOfic());
        e.setCodResp(r.codResp());
        e.setObserv(r.observ());
        e.setDiaAnt(r.diaAnt());
        e.setMesAnt(r.mesAnt());
        e.setAnoAnt(r.anoAnt());
        e.setVutAnt(r.vutAnt());
        e.setCostoAnt(r.costoAnt());
        e.setBandUfv(r.bandUfv());
        e.setCodEstado(r.codEstado());
        e.setCodRube(r.codRube());
        e.setNroConv(r.nroConv());
        e.setOrgFin(r.orgFin());
        e.setCodigoSec(r.codigoSec());
        e.setBanderas(r.banderas());
    }

    private void auditar(Actual e, String usuar, boolean creacion) {
        String usuario = (usuar == null || usuar.isBlank()) ? "api" : usuar;
        LocalDate hoy = LocalDate.now();
        e.setFeult(hoy);
        e.setUsuar(usuario);
        if (!creacion) {
            e.setFecMod(hoy);
            e.setUsuMod(usuario);
        }
    }

    private void validarReferencias(Actual e) {
        if (e.getEntidad() != null && !e.getEntidad().isBlank() && e.getUnidad() != null && !e.getUnidad().isBlank() && !unidadAdminRepository.existsById(new UnidadAdminId(e.getEntidad(), e.getUnidad()))) {
            throw new BadRequestException("No existe unidad administrativa con clave [" + String.valueOf(e.getEntidad()) + ", " + String.valueOf(e.getUnidad()) + "]");
        }
        if (e.getCodCont() != null && !codContRepository.existsById(e.getCodCont())) {
            throw new BadRequestException("No existe cuenta contable con clave [" + String.valueOf(e.getCodCont()) + "]");
        }
        if (e.getEntidad() != null && !e.getEntidad().isBlank() && e.getUnidad() != null && !e.getUnidad().isBlank() && e.getCodCont() != null && e.getCodCont() != 0 && e.getCodAux() != null && e.getCodAux() != 0 && !auxiliarRepository.existsById(new AuxiliarId(e.getEntidad(), e.getUnidad(), e.getCodCont(), e.getCodAux()))) {
            throw new BadRequestException("No existe auxiliar contable con clave [" + String.valueOf(e.getEntidad()) + ", " + String.valueOf(e.getUnidad()) + ", " + String.valueOf(e.getCodCont()) + ", " + String.valueOf(e.getCodAux()) + "]");
        }
        if (e.getEntidad() != null && !e.getEntidad().isBlank() && e.getUnidad() != null && !e.getUnidad().isBlank() && e.getCodOfic() != null && !oficinaRepository.existsById(new OficinaId(e.getEntidad(), e.getUnidad(), e.getCodOfic()))) {
            throw new BadRequestException("No existe oficina con clave [" + String.valueOf(e.getEntidad()) + ", " + String.valueOf(e.getUnidad()) + ", " + String.valueOf(e.getCodOfic()) + "]");
        }
        if (e.getEntidad() != null && !e.getEntidad().isBlank() && e.getUnidad() != null && !e.getUnidad().isBlank() && e.getCodOfic() != null && e.getCodResp() != null && !respRepository.existsById(new RespId(e.getEntidad(), e.getUnidad(), e.getCodOfic(), e.getCodResp()))) {
            throw new BadRequestException("No existe responsable con clave [" + String.valueOf(e.getEntidad()) + ", " + String.valueOf(e.getUnidad()) + ", " + String.valueOf(e.getCodOfic()) + ", " + String.valueOf(e.getCodResp()) + "]");
        }
        if (e.getCodEstado() != null && e.getCodEstado() != 0 && !estadoRepository.existsById(e.getCodEstado())) {
            throw new BadRequestException("No existe estado del bien con clave [" + String.valueOf(e.getCodEstado()) + "]");
        }
    }

    private ActualResponse toResponse(Actual e) {
        return new ActualResponse(
                e.getUnidad(),
                e.getEntidad(),
                e.getCodigo(),
                e.getCodCont(),
                e.getCodAux(),
                e.getVidaUtil(),
                e.getDescrip(),
                e.getCosto(),
                e.getDepAcu(),
                e.getMes(),
                e.getAno(),
                e.getBRev(),
                e.getDia(),
                e.getCodOfic(),
                e.getCodResp(),
                e.getObserv(),
                e.getDiaAnt(),
                e.getMesAnt(),
                e.getAnoAnt(),
                e.getVutAnt(),
                e.getCostoAnt(),
                e.getBandUfv(),
                e.getCodEstado(),
                e.getCodRube(),
                e.getNroConv(),
                e.getOrgFin(),
                e.getFeult(),
                e.getUsuar(),
                e.getApiEstado(),
                e.getCodigoSec(),
                e.getBanderas(),
                e.getFecMod(),
                e.getUsuMod(),
                e.getUnidadAdmin() != null ? e.getUnidadAdmin().getDescrip() : null,
                e.getCuenta() != null ? e.getCuenta().getNombre() : null,
                e.getAuxiliar() != null ? e.getAuxiliar().getNomAux() : null,
                e.getOficina() != null ? e.getOficina().getNomOfic() : null,
                e.getResponsable() != null ? e.getResponsable().getNomResp() : null,
                e.getEstadoBien() != null ? e.getEstadoBien().getNomEstado() : null
        );
    }
}
