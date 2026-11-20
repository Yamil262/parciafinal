package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.AuxiliarFilaDto;
import com.sis414.parciafinal.dto.GrupoDetalleDto;
import com.sis414.parciafinal.entity.Grupo;
import com.sis414.parciafinal.repository.AuxiliarRepository;
import com.sis414.parciafinal.repository.GrupoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Pantalla GRUPO CONTABLE. */
@Service
public class GrupoPantallaService {

    private final GrupoRepository grupoRepository;
    private final AuxiliarRepository auxiliarRepository;

    public GrupoPantallaService(GrupoRepository grupoRepository, AuxiliarRepository auxiliarRepository) {
        this.grupoRepository = grupoRepository;
        this.auxiliarRepository = auxiliarRepository;
    }

    @Transactional(readOnly = true)
    public GrupoDetalleDto detalle(Long id) {
        Grupo g = grupoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo no encontrado: " + id));
        return new GrupoDetalleDto(g.getId(), g.getCodCont(), g.getNombre(), g.getVidaUtil(), g.getObserv(),
                g.getDepreciar(), g.getActualizar(), auxiliares(id));
    }

    @Transactional(readOnly = true)
    public List<AuxiliarFilaDto> auxiliares(Long grupoId) {
        return auxiliarRepository.findByGrupoId(grupoId).stream()
                .map(a -> new AuxiliarFilaDto(a.getId(), a.getCodAux(), a.getNomAux()))
                .toList();
    }
}
