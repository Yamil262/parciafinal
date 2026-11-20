package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.RespaldarRequest;
import com.sis414.parciafinal.dto.RespaldoDto;
import com.sis414.parciafinal.entity.Respaldo;
import com.sis414.parciafinal.repository.RespaldoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Pantalla ADMINISTRACION DE BACKUPS. Solo registra el respaldo (archivo .zip, dia, hora, usuario, descripcion). */
@Service
public class RespaldoPantallaService {

    private final RespaldoRepository repository;

    public RespaldoPantallaService(RespaldoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public RespaldoDto respaldar(RespaldarRequest req) {
        LocalDateTime now = LocalDateTime.now();
        Respaldo r = new Respaldo();
        r.setDia(now.toLocalDate());
        r.setHora(now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        r.setArchivo(now.format(DateTimeFormatter.ofPattern("yyMMddHHmmss")));
        r.setObserv(req == null ? null : req.descripcion());
        String usuario = req == null ? null : req.usuario();
        r.setUsuar(usuario == null ? null : (usuario.length() > 8 ? usuario.substring(0, 8) : usuario));
        r.setFeult(now.toLocalDate());
        Respaldo g = repository.save(r);
        return new RespaldoDto(g.getId(), g.getDia(), g.getHora(), g.getArchivo(), g.getObserv(), g.getUsuar(), g.getFeult());
    }
}
