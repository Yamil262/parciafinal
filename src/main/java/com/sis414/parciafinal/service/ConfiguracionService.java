package com.sis414.parciafinal.service;

import com.sis414.parciafinal.entity.OrgUser;
import com.sis414.parciafinal.repository.OrgUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/** Guarda la configuracion del sistema (gestion, unidad seleccionada, directorio de la BD) en la tabla ORGUSER. */
@Service
public class ConfiguracionService {

    private static final String TIPO = "CONFIG";
    public static final String GESTION = "GESTION";
    public static final String UNIDAD = "UNIDAD";
    public static final String DIRECTORIO = "DIRECTORIO";

    private final OrgUserRepository repository;

    public ConfiguracionService(OrgUserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public String get(String clave, String porDefecto) {
        return repository.findByTypeAndIdUser(TIPO, clave)
                .map(OrgUser::getData)
                .filter(s -> !s.isBlank())
                .orElse(porDefecto);
    }

    @Transactional
    public void set(String clave, String valor) {
        OrgUser o = repository.findByTypeAndIdUser(TIPO, clave).orElseGet(() -> {
            OrgUser n = new OrgUser();
            n.setType(TIPO);
            n.setIdUser(clave);
            n.setName(clave);
            n.setReadonly(false);
            n.setCkval(0);
            return n;
        });
        o.setData(valor);
        o.setUpdated(LocalDate.now());
        repository.save(o);
    }

    @Transactional(readOnly = true)
    public int gestion() {
        try {
            return Integer.parseInt(get(GESTION, ""));
        } catch (NumberFormatException e) {
            return LocalDate.now().getYear();
        }
    }

    @Transactional(readOnly = true)
    public Long unidadSeleccionadaId() {
        try {
            return Long.parseLong(get(UNIDAD, ""));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
