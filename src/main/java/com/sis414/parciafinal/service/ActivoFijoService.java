package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.ActivoFijoDto;
import com.sis414.parciafinal.entity.ActivoFijo;
import com.sis414.parciafinal.repository.ActivoFijoRepository;
import com.sis414.parciafinal.repository.AuxiliarRepository;
import com.sis414.parciafinal.repository.EstadoRepository;
import com.sis414.parciafinal.repository.GrupoRepository;
import com.sis414.parciafinal.repository.OficinaRepository;
import com.sis414.parciafinal.repository.OrganismoFinRepository;
import com.sis414.parciafinal.repository.ResponsableRepository;
import com.sis414.parciafinal.repository.TransferenciaRepository;
import com.sis414.parciafinal.repository.UnidadAdminRepository;
import com.sis414.parciafinal.dto.TransferirRequest;
import com.sis414.parciafinal.entity.Transferencia;
import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActivoFijoService {

    private final ActivoFijoRepository repository;
    private final AuxiliarRepository auxiliarRepository;
    private final EstadoRepository estadoRepository;
    private final GrupoRepository grupoRepository;
    private final OficinaRepository oficinaRepository;
    private final OrganismoFinRepository organismoFinRepository;
    private final ResponsableRepository responsableRepository;
    private final UnidadAdminRepository unidadAdminRepository;
    private final TransferenciaRepository transferenciaRepository;

    public ActivoFijoService(ActivoFijoRepository repository, AuxiliarRepository auxiliarRepository, EstadoRepository estadoRepository, GrupoRepository grupoRepository, OficinaRepository oficinaRepository, OrganismoFinRepository organismoFinRepository, ResponsableRepository responsableRepository, UnidadAdminRepository unidadAdminRepository, TransferenciaRepository transferenciaRepository) {
        this.repository = repository;
        this.auxiliarRepository = auxiliarRepository;
        this.estadoRepository = estadoRepository;
        this.grupoRepository = grupoRepository;
        this.oficinaRepository = oficinaRepository;
        this.organismoFinRepository = organismoFinRepository;
        this.responsableRepository = responsableRepository;
        this.unidadAdminRepository = unidadAdminRepository;
        this.transferenciaRepository = transferenciaRepository;
    }

    @Transactional(readOnly = true)
    public List<ActivoFijoDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public ActivoFijoDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public ActivoFijoDto create(ActivoFijoDto dto) {
        ActivoFijo entity = new ActivoFijo();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public ActivoFijoDto update(Long id, ActivoFijoDto dto) {
        ActivoFijo entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public ActivoFijo getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ActivoFijo no encontrado: " + id));
    }

    public ActivoFijoDto toDto(ActivoFijo e) {
        return new ActivoFijoDto(
                e.getId(),
                e.getCodigo(),
                e.getVidaUtil(),
                e.getDescrip(),
                e.getCosto(),
                e.getDepacu(),
                e.getMes(),
                e.getAno(),
                e.getBrev(),
                e.getDia(),
                e.getObserv(),
                e.getDiaAnt(),
                e.getMesAnt(),
                e.getAnoAnt(),
                e.getVutAnt(),
                e.getCostoAnt(),
                e.getBandUfv(),
                e.getCodRube(),
                e.getNroConv(),
                e.getFeult(),
                e.getUsuar(),
                e.getApiEstado(),
                e.getCodigoSec(),
                e.getBanderas(),
                e.getFecMod(),
                e.getUsuMod(),
                e.getUnidad() == null ? null : e.getUnidad().getId(),
                e.getGrupo() == null ? null : e.getGrupo().getId(),
                e.getAuxiliar() == null ? null : e.getAuxiliar().getId(),
                e.getOficina() == null ? null : e.getOficina().getId(),
                e.getResponsable() == null ? null : e.getResponsable().getId(),
                e.getEstado() == null ? null : e.getEstado().getId(),
                e.getOrganismoFin() == null ? null : e.getOrganismoFin().getId()
        );
    }

    private void apply(ActivoFijoDto dto, ActivoFijo e) {
        e.setCodigo(dto.codigo());
        e.setVidaUtil(dto.vidaUtil());
        e.setDescrip(dto.descrip());
        e.setCosto(dto.costo());
        e.setDepacu(dto.depacu());
        e.setMes(dto.mes());
        e.setAno(dto.ano());
        e.setBrev(dto.brev());
        e.setDia(dto.dia());
        e.setObserv(dto.observ());
        e.setDiaAnt(dto.diaAnt());
        e.setMesAnt(dto.mesAnt());
        e.setAnoAnt(dto.anoAnt());
        e.setVutAnt(dto.vutAnt());
        e.setCostoAnt(dto.costoAnt());
        e.setBandUfv(dto.bandUfv());
        e.setCodRube(dto.codRube());
        e.setNroConv(dto.nroConv());
        e.setFeult(dto.feult());
        e.setUsuar(dto.usuar());
        e.setApiEstado(dto.apiEstado());
        e.setCodigoSec(dto.codigoSec());
        e.setBanderas(dto.banderas());
        e.setFecMod(dto.fecMod());
        e.setUsuMod(dto.usuMod());
        e.setUnidad(dto.unidadId() == null ? null : unidadAdminRepository.getReferenceById(dto.unidadId()));
        e.setGrupo(dto.grupoId() == null ? null : grupoRepository.getReferenceById(dto.grupoId()));
        e.setAuxiliar(dto.auxiliarId() == null ? null : auxiliarRepository.getReferenceById(dto.auxiliarId()));
        e.setOficina(dto.oficinaId() == null ? null : oficinaRepository.getReferenceById(dto.oficinaId()));
        e.setResponsable(dto.responsableId() == null ? null : responsableRepository.getReferenceById(dto.responsableId()));
        e.setEstado(dto.estadoId() == null ? null : estadoRepository.getReferenceById(dto.estadoId()));
        e.setOrganismoFin(dto.organismoFinId() == null ? null : organismoFinRepository.getReferenceById(dto.organismoFinId()));
    }

    @Transactional
    public ActivoFijoDto transferir(Long id, TransferirRequest req) {
        ActivoFijo a = getOrThrow(id);
        Transferencia t = new Transferencia();
        t.setUnidad(a.getUnidad());
        t.setUnidadInicial(a.getUnidad());
        t.setActivo(a);
        t.setCodigo(a.getCodigo());
        t.setOficina(oficinaRepository.getReferenceById(req.oficinaId()));
        t.setResponsable(responsableRepository.getReferenceById(req.responsableId()));
        t.setFeult(LocalDate.now());
        t.setUsuar(req.usuar());
        transferenciaRepository.save(t);
        a.setOficina(t.getOficina());
        a.setResponsable(t.getResponsable());
        return toDto(repository.save(a));
    }

    /** apiEstado: 0 = ELABORADO, 1 = APROBADO (supuesto según la pantalla del sistema). */
    @Transactional
    public ActivoFijoDto aprobar(Long id) {
        ActivoFijo a = getOrThrow(id);
        a.setApiEstado(1);
        return toDto(repository.save(a));
    }
}
