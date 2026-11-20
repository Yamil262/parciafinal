package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.BajaActivoDto;
import com.sis414.parciafinal.entity.BajaActivo;
import com.sis414.parciafinal.repository.AuxiliarRepository;
import com.sis414.parciafinal.repository.BajaActivoRepository;
import com.sis414.parciafinal.repository.GrupoRepository;
import com.sis414.parciafinal.repository.MotivoBajaRepository;
import com.sis414.parciafinal.repository.OficinaRepository;
import com.sis414.parciafinal.repository.ResponsableRepository;
import com.sis414.parciafinal.repository.UnidadAdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BajaActivoService {

    private final BajaActivoRepository repository;
    private final AuxiliarRepository auxiliarRepository;
    private final GrupoRepository grupoRepository;
    private final MotivoBajaRepository motivoBajaRepository;
    private final OficinaRepository oficinaRepository;
    private final ResponsableRepository responsableRepository;
    private final UnidadAdminRepository unidadAdminRepository;

    public BajaActivoService(BajaActivoRepository repository, AuxiliarRepository auxiliarRepository, GrupoRepository grupoRepository, MotivoBajaRepository motivoBajaRepository, OficinaRepository oficinaRepository, ResponsableRepository responsableRepository, UnidadAdminRepository unidadAdminRepository) {
        this.repository = repository;
        this.auxiliarRepository = auxiliarRepository;
        this.grupoRepository = grupoRepository;
        this.motivoBajaRepository = motivoBajaRepository;
        this.oficinaRepository = oficinaRepository;
        this.responsableRepository = responsableRepository;
        this.unidadAdminRepository = unidadAdminRepository;
    }

    @Transactional(readOnly = true)
    public List<BajaActivoDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public BajaActivoDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public BajaActivoDto create(BajaActivoDto dto) {
        BajaActivo entity = new BajaActivo();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public BajaActivoDto update(Long id, BajaActivoDto dto) {
        BajaActivo entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public BajaActivo getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BajaActivo no encontrado: " + id));
    }

    public BajaActivoDto toDto(BajaActivo e) {
        return new BajaActivoDto(
                e.getId(),
                e.getCodigo(),
                e.getCosto(),
                e.getDepacuAnt(),
                e.getDescrip(),
                e.getDiaBaja(),
                e.getDia(),
                e.getMesBaja(),
                e.getMes(),
                e.getAnoBaja(),
                e.getAno(),
                e.getBrev(),
                e.getVidaUtil(),
                e.getResolucion(),
                e.getObserv(),
                e.getMotivo(),
                e.getDepacu(),
                e.getActua(),
                e.getDepGestion(),
                e.getCosBajIni(),
                e.getCosDaIni(),
                e.getCosActBaj(),
                e.getActDaBaj(),
                e.getFeult(),
                e.getUsuar(),
                e.getFecMod(),
                e.getUsuMod(),
                e.getUnidad() == null ? null : e.getUnidad().getId(),
                e.getMotivoBaja() == null ? null : e.getMotivoBaja().getId(),
                e.getGrupo() == null ? null : e.getGrupo().getId(),
                e.getAuxiliar() == null ? null : e.getAuxiliar().getId(),
                e.getOficina() == null ? null : e.getOficina().getId(),
                e.getResponsable() == null ? null : e.getResponsable().getId()
        );
    }

    private void apply(BajaActivoDto dto, BajaActivo e) {
        e.setCodigo(dto.codigo());
        e.setCosto(dto.costo());
        e.setDepacuAnt(dto.depacuAnt());
        e.setDescrip(dto.descrip());
        e.setDiaBaja(dto.diaBaja());
        e.setDia(dto.dia());
        e.setMesBaja(dto.mesBaja());
        e.setMes(dto.mes());
        e.setAnoBaja(dto.anoBaja());
        e.setAno(dto.ano());
        e.setBrev(dto.brev());
        e.setVidaUtil(dto.vidaUtil());
        e.setResolucion(dto.resolucion());
        e.setObserv(dto.observ());
        e.setMotivo(dto.motivo());
        e.setDepacu(dto.depacu());
        e.setActua(dto.actua());
        e.setDepGestion(dto.depGestion());
        e.setCosBajIni(dto.cosBajIni());
        e.setCosDaIni(dto.cosDaIni());
        e.setCosActBaj(dto.cosActBaj());
        e.setActDaBaj(dto.actDaBaj());
        e.setFeult(dto.feult());
        e.setUsuar(dto.usuar());
        e.setFecMod(dto.fecMod());
        e.setUsuMod(dto.usuMod());
        e.setUnidad(dto.unidadId() == null ? null : unidadAdminRepository.getReferenceById(dto.unidadId()));
        e.setMotivoBaja(dto.motivoBajaId() == null ? null : motivoBajaRepository.getReferenceById(dto.motivoBajaId()));
        e.setGrupo(dto.grupoId() == null ? null : grupoRepository.getReferenceById(dto.grupoId()));
        e.setAuxiliar(dto.auxiliarId() == null ? null : auxiliarRepository.getReferenceById(dto.auxiliarId()));
        e.setOficina(dto.oficinaId() == null ? null : oficinaRepository.getReferenceById(dto.oficinaId()));
        e.setResponsable(dto.responsableId() == null ? null : responsableRepository.getReferenceById(dto.responsableId()));
    }
}
