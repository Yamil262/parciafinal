package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.OficinaDetalleDto;
import com.sis414.parciafinal.dto.ResponsableFilaDto;
import com.sis414.parciafinal.entity.Oficina;
import com.sis414.parciafinal.entity.Responsable;
import com.sis414.parciafinal.repository.OficinaRepository;
import com.sis414.parciafinal.repository.ResponsableRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Pantalla OFICINA: botones Activar / Inactivar de oficinas y responsables. apiEstado: 1 = ACTIVO, 0 = INACTIVO (supuesto). */
@Service
public class OficinaResponsablePantallaService {

    private final OficinaRepository oficinaRepository;
    private final ResponsableRepository responsableRepository;

    public OficinaResponsablePantallaService(OficinaRepository oficinaRepository, ResponsableRepository responsableRepository) {
        this.oficinaRepository = oficinaRepository;
        this.responsableRepository = responsableRepository;
    }

    @Transactional(readOnly = true)
    public OficinaDetalleDto detalle(Long id) {
        Oficina o = oficina(id);
        return new OficinaDetalleDto(o.getId(), o.getCodOfic(), o.getNomOfic(), estado(o.getApiEstado()),
                o.getObserv(), responsables(id));
    }

    @Transactional(readOnly = true)
    public List<ResponsableFilaDto> responsables(Long oficinaId) {
        oficina(oficinaId);
        return responsableRepository.findByOficinaId(oficinaId).stream().map(this::fila).toList();
    }

    @Transactional
    public OficinaDetalleDto cambiarEstadoOficina(Long id, int estado) {
        Oficina o = oficina(id);
        o.setApiEstado(estado);
        oficinaRepository.save(o);
        return detalle(id);
    }

    @Transactional
    public ResponsableFilaDto cambiarEstadoResponsable(Long id, int estado) {
        Responsable r = responsableRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsable no encontrado: " + id));
        r.setApiEstado(estado);
        return fila(responsableRepository.save(r));
    }

    private Oficina oficina(Long id) {
        return oficinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Oficina no encontrada: " + id));
    }

    private ResponsableFilaDto fila(Responsable r) {
        return new ResponsableFilaDto(r.getId(), r.getCodResp(), r.getNomResp(), r.getCargo(), r.getCi(),
                r.getDepartamentoExp() == null ? null : r.getDepartamentoExp().getSigla(), estado(r.getApiEstado()));
    }

    private static String estado(Integer e) {
        return Integer.valueOf(1).equals(e) ? "ACTIVO" : "INACTIVO";
    }
}
