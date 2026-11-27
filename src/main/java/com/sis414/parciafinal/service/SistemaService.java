package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.AcercaDto;
import com.sis414.parciafinal.dto.ContextoDto;
import com.sis414.parciafinal.entity.Entidad;
import com.sis414.parciafinal.entity.UnidadAdmin;
import com.sis414.parciafinal.repository.EntidadRepository;
import com.sis414.parciafinal.repository.RespaldoRepository;
import com.sis414.parciafinal.repository.UnidadAdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Pantalla principal del VSIAF. */
@Service
public class SistemaService {

    private final ConfiguracionService configuracion;
    private final UnidadAdminRepository unidadRepository;
    private final EntidadRepository entidadRepository;
    private final RespaldoRepository respaldoRepository;

    public SistemaService(ConfiguracionService configuracion, UnidadAdminRepository unidadRepository,
                          EntidadRepository entidadRepository, RespaldoRepository respaldoRepository) {
        this.configuracion = configuracion;
        this.unidadRepository = unidadRepository;
        this.entidadRepository = entidadRepository;
        this.respaldoRepository = respaldoRepository;
    }

    @Transactional(readOnly = true)
    public ContextoDto contexto() {
        String[] sel = configuracion.unidadSeleccionada();
        UnidadAdmin u = sel == null ? null : unidadRepository.findById(new UnidadAdmin.Key(sel[0], sel[1])).orElse(null);
        Entidad e = u == null ? null : entidadRepository.findFirstByEntidadOrderByGestionDesc(u.getEntidad()).orElse(null);
        long respaldos = respaldoRepository.count();
        return new ContextoDto(
                u == null ? "0" : u.getEntidad(),
                e == null ? null : e.getDescEnt(),
                u == null ? null : u.getUnidad(),
                u == null ? null : u.getDescrip(),
                configuracion.gestion(),
                respaldos == 0 ? "None" : String.valueOf(respaldos));
    }

    public AcercaDto acerca() {
        return new AcercaDto("VSIAF - Sistema de Activos Fijos", "3.2", "Copyright 1999-2013 DGSGIF. Todos los derechos reservados");
    }
}
