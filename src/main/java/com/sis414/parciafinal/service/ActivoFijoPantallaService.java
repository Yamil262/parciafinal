package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.ActivoFijoDto;
import com.sis414.parciafinal.dto.ActivoFijoFichaDto;
import com.sis414.parciafinal.dto.CalculoActivoDto;
import com.sis414.parciafinal.dto.DuplicarRequest;
import com.sis414.parciafinal.entity.ActivoFijo;
import com.sis414.parciafinal.entity.Grupo;
import com.sis414.parciafinal.repository.ActivoFijoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/** Botones y navegacion de la pantalla ADMINISTRACION DE ACTIVOS FIJOS. */
@Service
public class ActivoFijoPantallaService {

    private final ActivoFijoRepository repository;
    private final ActivoFijoService activoFijoService;
    private final CalculoActivoService calculoService;

    public ActivoFijoPantallaService(ActivoFijoRepository repository, ActivoFijoService activoFijoService,
                                     CalculoActivoService calculoService) {
        this.repository = repository;
        this.activoFijoService = activoFijoService;
        this.calculoService = calculoService;
    }

    @Transactional(readOnly = true)
    public ActivoFijoFichaDto ficha(Long id, Integer dia, Integer mes, Integer ano, BigDecimal ufvInc, BigDecimal ufvCalc) {
        return toFicha(activoFijoService.getOrThrow(id), dia, mes, ano, ufvInc, ufvCalc);
    }

    @Transactional(readOnly = true)
    public ActivoFijoFichaDto primero(Integer dia, Integer mes, Integer ano, BigDecimal ufvInc, BigDecimal ufvCalc) {
        return toFicha(noHay(repository.findFirstByOrderByIdAsc()), dia, mes, ano, ufvInc, ufvCalc);
    }

    @Transactional(readOnly = true)
    public ActivoFijoFichaDto ultimo(Integer dia, Integer mes, Integer ano, BigDecimal ufvInc, BigDecimal ufvCalc) {
        return toFicha(noHay(repository.findFirstByOrderByIdDesc()), dia, mes, ano, ufvInc, ufvCalc);
    }

    @Transactional(readOnly = true)
    public ActivoFijoFichaDto siguiente(Long id, Integer dia, Integer mes, Integer ano, BigDecimal ufvInc, BigDecimal ufvCalc) {
        return toFicha(noHay(repository.findFirstByIdGreaterThanOrderByIdAsc(id)), dia, mes, ano, ufvInc, ufvCalc);
    }

    @Transactional(readOnly = true)
    public ActivoFijoFichaDto anterior(Long id, Integer dia, Integer mes, Integer ano, BigDecimal ufvInc, BigDecimal ufvCalc) {
        return toFicha(noHay(repository.findFirstByIdLessThanOrderByIdDesc(id)), dia, mes, ano, ufvInc, ufvCalc);
    }

    @Transactional(readOnly = true)
    public List<ActivoFijoDto> buscar(String codigo, String descripcion, Long grupoId, Long oficinaId, Long responsableId) {
        return repository.findAll().stream()
                .filter(a -> contiene(a.getCodigo(), codigo))
                .filter(a -> contiene(a.getDescrip(), descripcion))
                .filter(a -> grupoId == null || (a.getGrupo() != null && grupoId.equals(a.getGrupo().getId())))
                .filter(a -> oficinaId == null || (a.getOficina() != null && oficinaId.equals(a.getOficina().getId())))
                .filter(a -> responsableId == null || (a.getResponsable() != null && responsableId.equals(a.getResponsable().getId())))
                .map(activoFijoService::toDto)
                .toList();
    }

    @Transactional
    public ActivoFijoDto duplicar(Long id, DuplicarRequest req) {
        ActivoFijo o = activoFijoService.getOrThrow(id);
        String nuevo = (req != null && req.nuevoCodigo() != null && !req.nuevoCodigo().isBlank())
                ? req.nuevoCodigo().trim() : siguienteCodigo(o);
        boolean existe = repository.findAll().stream().anyMatch(x -> nuevo.equals(x.getCodigo())
                && x.getUnidad() != null && o.getUnidad() != null && x.getUnidad().getId().equals(o.getUnidad().getId()));
        if (existe) throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un activo con el codigo " + nuevo);

        ActivoFijo n = new ActivoFijo();
        n.setCodigo(nuevo);
        n.setVidaUtil(o.getVidaUtil());
        n.setDescrip(o.getDescrip());
        n.setCosto(o.getCosto());
        n.setDepacu(o.getDepacu());
        n.setMes(o.getMes());
        n.setAno(o.getAno());
        n.setBrev(o.getBrev());
        n.setDia(o.getDia());
        n.setObserv(o.getObserv());
        n.setDiaAnt(o.getDiaAnt());
        n.setMesAnt(o.getMesAnt());
        n.setAnoAnt(o.getAnoAnt());
        n.setVutAnt(o.getVutAnt());
        n.setCostoAnt(o.getCostoAnt());
        n.setBandUfv(o.getBandUfv());
        n.setNroConv(o.getNroConv());
        n.setBanderas(o.getBanderas());
        n.setUnidad(o.getUnidad());
        n.setGrupo(o.getGrupo());
        n.setAuxiliar(o.getAuxiliar());
        n.setOficina(o.getOficina());
        n.setResponsable(o.getResponsable());
        n.setEstado(o.getEstado());
        n.setOrganismoFin(o.getOrganismoFin());
        n.setApiEstado(0);
        n.setFeult(LocalDate.now());
        n.setUsuar(req == null ? null : req.usuar());
        return activoFijoService.toDto(repository.save(n));
    }

    private String siguienteCodigo(ActivoFijo o) {
        String base = o.getCodigo() == null ? "" : o.getCodigo();
        for (int i = 2; i < 1000; i++) {
            String sufijo = "-" + i;
            String c = (base.length() + sufijo.length() > 15 ? base.substring(0, 15 - sufijo.length()) : base) + sufijo;
            boolean usado = repository.findAll().stream().anyMatch(x -> c.equals(x.getCodigo()));
            if (!usado) return c;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "No se pudo generar un codigo nuevo");
    }

    private ActivoFijoFichaDto toFicha(ActivoFijo a, Integer dia, Integer mes, Integer ano, BigDecimal ufvInc, BigDecimal ufvCalc) {
        LocalDate fecha = calculoService.fecha(dia, mes, ano);
        BigDecimal ui = ufvInc == null ? BigDecimal.ONE : ufvInc;
        BigDecimal uc = ufvCalc == null ? ui : ufvCalc;
        CalculoActivoDto c = calculoService.calcular(a, fecha, ui, uc);
        Grupo g = a.getGrupo();
        return new ActivoFijoFichaDto(
                a.getId(), a.getCodigo(), a.getCodigoSec(),
                a.getDia(), a.getMes(), a.getAno(), ui,
                a.getDescrip(),
                g == null ? null : g.getNombre(),
                a.getAuxiliar() == null ? null : a.getAuxiliar().getNomAux(),
                a.getOficina() == null ? null : a.getOficina().getNomOfic(),
                a.getResponsable() == null ? null : a.getResponsable().getNomResp(),
                a.getResponsable() == null ? null : a.getResponsable().getCargo(),
                a.getEstado() == null ? null : a.getEstado().getNomEstado(),
                a.getObserv(), a.getCodRube(),
                a.getOrganismoFin() == null ? null : a.getOrganismoFin().getCodigo(),
                a.getNroConv(),
                g == null ? null : g.getDepreciar(),
                g == null ? null : g.getActualizar(),
                Integer.valueOf(1).equals(a.getApiEstado()) ? "APROBADO" : "ELABORADO",
                c);
    }

    private static boolean contiene(String valor, String filtro) {
        if (filtro == null || filtro.isBlank()) return true;
        return valor != null && valor.toLowerCase().contains(filtro.toLowerCase());
    }

    private static ActivoFijo noHay(Optional<ActivoFijo> o) {
        return o.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay mas activos en esa direccion"));
    }
}
