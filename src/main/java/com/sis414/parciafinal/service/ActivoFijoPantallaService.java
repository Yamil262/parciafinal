package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.ActivoFijoFichaDto;
import com.sis414.parciafinal.dto.CalculoActivoDto;
import com.sis414.parciafinal.dto.DuplicarRequest;
import com.sis414.parciafinal.dto.TransferirRequest;
import com.sis414.parciafinal.entity.ActivoFijo;
import com.sis414.parciafinal.entity.Auxiliar;
import com.sis414.parciafinal.entity.Estado;
import com.sis414.parciafinal.entity.Grupo;
import com.sis414.parciafinal.entity.Oficina;
import com.sis414.parciafinal.entity.OrganismoFin;
import com.sis414.parciafinal.entity.Responsable;
import com.sis414.parciafinal.entity.Transferencia;
import com.sis414.parciafinal.repository.ActivoFijoRepository;
import com.sis414.parciafinal.repository.AuxiliarRepository;
import com.sis414.parciafinal.repository.EstadoRepository;
import com.sis414.parciafinal.repository.GrupoRepository;
import com.sis414.parciafinal.repository.OficinaRepository;
import com.sis414.parciafinal.repository.OrganismoFinRepository;
import com.sis414.parciafinal.repository.ResponsableRepository;
import com.sis414.parciafinal.repository.TransferenciaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/** Botones y navegación de la pantalla ADMINISTRACION DE ACTIVOS FIJOS (tabla ACTUAL). */
@Service
public class ActivoFijoPantallaService {

    private final ActivoFijoRepository repository;
    private final GrupoRepository grupoRepository;
    private final AuxiliarRepository auxiliarRepository;
    private final OficinaRepository oficinaRepository;
    private final ResponsableRepository responsableRepository;
    private final EstadoRepository estadoRepository;
    private final OrganismoFinRepository organismoFinRepository;
    private final TransferenciaRepository transferenciaRepository;
    private final CalculoActivoService calculoService;

    public ActivoFijoPantallaService(ActivoFijoRepository repository, GrupoRepository grupoRepository,
                                     AuxiliarRepository auxiliarRepository, OficinaRepository oficinaRepository,
                                     ResponsableRepository responsableRepository, EstadoRepository estadoRepository,
                                     OrganismoFinRepository organismoFinRepository,
                                     TransferenciaRepository transferenciaRepository,
                                     CalculoActivoService calculoService) {
        this.repository = repository;
        this.grupoRepository = grupoRepository;
        this.auxiliarRepository = auxiliarRepository;
        this.oficinaRepository = oficinaRepository;
        this.responsableRepository = responsableRepository;
        this.estadoRepository = estadoRepository;
        this.organismoFinRepository = organismoFinRepository;
        this.transferenciaRepository = transferenciaRepository;
        this.calculoService = calculoService;
    }

    public ActivoFijo get(String entidad, String unidad, String codigo) {
        return repository.findById(new ActivoFijo.Key(entidad, unidad, codigo))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Activo fijo no encontrado: " + entidad + "/" + unidad + "/" + codigo));
    }

    @Transactional(readOnly = true)
    public ActivoFijoFichaDto ficha(String entidad, String unidad, String codigo, Integer dia, Integer mes, Integer ano,
                                    BigDecimal ufvInc, BigDecimal ufvCalc) {
        return toFicha(get(entidad, unidad, codigo), dia, mes, ano, ufvInc, ufvCalc);
    }

    @Transactional(readOnly = true)
    public ActivoFijoFichaDto primero(String entidad, String unidad, Integer dia, Integer mes, Integer ano,
                                      BigDecimal ufvInc, BigDecimal ufvCalc) {
        return toFicha(noHay(repository.findFirstByEntidadAndUnidadOrderByCodigoAsc(entidad, unidad)), dia, mes, ano, ufvInc, ufvCalc);
    }

    @Transactional(readOnly = true)
    public ActivoFijoFichaDto ultimo(String entidad, String unidad, Integer dia, Integer mes, Integer ano,
                                     BigDecimal ufvInc, BigDecimal ufvCalc) {
        return toFicha(noHay(repository.findFirstByEntidadAndUnidadOrderByCodigoDesc(entidad, unidad)), dia, mes, ano, ufvInc, ufvCalc);
    }

    @Transactional(readOnly = true)
    public ActivoFijoFichaDto siguiente(String entidad, String unidad, String codigo, Integer dia, Integer mes, Integer ano,
                                        BigDecimal ufvInc, BigDecimal ufvCalc) {
        return toFicha(noHay(repository.findFirstByEntidadAndUnidadAndCodigoGreaterThanOrderByCodigoAsc(entidad, unidad, codigo)),
                dia, mes, ano, ufvInc, ufvCalc);
    }

    @Transactional(readOnly = true)
    public ActivoFijoFichaDto anterior(String entidad, String unidad, String codigo, Integer dia, Integer mes, Integer ano,
                                       BigDecimal ufvInc, BigDecimal ufvCalc) {
        return toFicha(noHay(repository.findFirstByEntidadAndUnidadAndCodigoLessThanOrderByCodigoDesc(entidad, unidad, codigo)),
                dia, mes, ano, ufvInc, ufvCalc);
    }

    @Transactional(readOnly = true)
    public List<ActivoFijo> buscar(String entidad, String unidad, String codigo, String descripcion,
                                   Integer codCont, Integer codOfic, Integer codResp) {
        List<ActivoFijo> base = (entidad != null && unidad != null)
                ? repository.findByEntidadAndUnidadOrderByCodigoAsc(entidad, unidad)
                : repository.findAll();
        return base.stream()
                .filter(a -> contiene(a.getCodigo(), codigo))
                .filter(a -> contiene(a.getDescrip(), descripcion))
                .filter(a -> codCont == null || codCont.equals(a.getCodCont()))
                .filter(a -> codOfic == null || codOfic.equals(a.getCodOfic()))
                .filter(a -> codResp == null || codResp.equals(a.getCodResp()))
                .toList();
    }

    @Transactional
    public ActivoFijo duplicar(String entidad, String unidad, String codigo, DuplicarRequest req) {
        ActivoFijo o = get(entidad, unidad, codigo);
        String nuevo = (req != null && req.nuevoCodigo() != null && !req.nuevoCodigo().isBlank())
                ? req.nuevoCodigo().trim() : siguienteCodigo(o);
        if (repository.existsById(new ActivoFijo.Key(entidad, unidad, nuevo))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un activo con el codigo " + nuevo);
        }
        ActivoFijo n = new ActivoFijo();
        n.setEntidad(o.getEntidad());
        n.setUnidad(o.getUnidad());
        n.setCodigo(nuevo);
        n.setCodCont(o.getCodCont());
        n.setCodAux(o.getCodAux());
        n.setVidaUtil(o.getVidaUtil());
        n.setDescrip(o.getDescrip());
        n.setCosto(o.getCosto());
        n.setDepacu(o.getDepacu());
        n.setMes(o.getMes());
        n.setAno(o.getAno());
        n.setBrev(o.getBrev());
        n.setDia(o.getDia());
        n.setCodOfic(o.getCodOfic());
        n.setCodResp(o.getCodResp());
        n.setObserv(o.getObserv());
        n.setDiaAnt(o.getDiaAnt());
        n.setMesAnt(o.getMesAnt());
        n.setAnoAnt(o.getAnoAnt());
        n.setVutAnt(o.getVutAnt());
        n.setCostoAnt(o.getCostoAnt());
        n.setBandUfv(o.getBandUfv());
        n.setCodEstado(o.getCodEstado());
        n.setNroConv(o.getNroConv());
        n.setOrgFin(o.getOrgFin());
        n.setBanderas(o.getBanderas());
        n.setApiEstado(0);
        n.setFeult(LocalDate.now());
        n.setUsuar(req == null ? null : recortar(req.usuar(), 8));
        return repository.save(n);
    }

    @Transactional
    public ActivoFijo transferir(String entidad, String unidad, String codigo, TransferirRequest req) {
        if (req == null || req.codOfic() == null || req.codResp() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Faltan codOfic y codResp");
        }
        if (!responsableRepository.existsById(new Responsable.Key(entidad, unidad, req.codOfic(), req.codResp()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No existe el responsable " + req.codResp() + " en la oficina " + req.codOfic());
        }
        ActivoFijo a = get(entidad, unidad, codigo);
        Transferencia t = new Transferencia();
        t.setEntidad(entidad);
        t.setUnidad(unidad);
        t.setCodigo(codigo);
        t.setCodOfic(req.codOfic());
        t.setCodResp(req.codResp());
        t.setUnidadIni(unidad);
        t.setFeult(LocalDate.now());
        t.setUsuar(recortar(req.usuar(), 8));
        transferenciaRepository.save(t);
        a.setCodOfic(req.codOfic());
        a.setCodResp(req.codResp());
        a.setFeult(LocalDate.now());
        return repository.save(a);
    }

    /** apiEstado: 0 = ELABORADO, 1 = APROBADO. */
    @Transactional
    public ActivoFijo aprobar(String entidad, String unidad, String codigo) {
        ActivoFijo a = get(entidad, unidad, codigo);
        a.setApiEstado(1);
        return repository.save(a);
    }

    private String siguienteCodigo(ActivoFijo o) {
        String base = o.getCodigo() == null ? "" : o.getCodigo();
        for (int i = 2; i < 1000; i++) {
            String sufijo = "-" + i;
            String c = (base.length() + sufijo.length() > 15 ? base.substring(0, 15 - sufijo.length()) : base) + sufijo;
            if (!repository.existsById(new ActivoFijo.Key(o.getEntidad(), o.getUnidad(), c))) return c;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "No se pudo generar un codigo nuevo");
    }

    private ActivoFijoFichaDto toFicha(ActivoFijo a, Integer dia, Integer mes, Integer ano, BigDecimal ufvInc, BigDecimal ufvCalc) {
        LocalDate fecha = calculoService.fecha(dia, mes, ano);
        BigDecimal ui = ufvInc == null ? BigDecimal.ONE : ufvInc;
        BigDecimal uc = ufvCalc == null ? ui : ufvCalc;

        Grupo g = a.getCodCont() == null ? null : grupoRepository.findById(a.getCodCont()).orElse(null);
        Auxiliar x = (a.getCodCont() == null || a.getCodAux() == null) ? null
                : auxiliarRepository.findById(new Auxiliar.Key(a.getEntidad(), a.getUnidad(), a.getCodCont(), a.getCodAux())).orElse(null);
        Oficina of = a.getCodOfic() == null ? null
                : oficinaRepository.findById(new Oficina.Key(a.getEntidad(), a.getUnidad(), a.getCodOfic())).orElse(null);
        Responsable r = (a.getCodOfic() == null || a.getCodResp() == null) ? null
                : responsableRepository.findById(new Responsable.Key(a.getEntidad(), a.getUnidad(), a.getCodOfic(), a.getCodResp())).orElse(null);
        Estado es = a.getCodEstado() == null ? null : estadoRepository.findById(a.getCodEstado()).orElse(null);
        Optional<OrganismoFin> org = (a.getOrgFin() == null || a.getOrgFin().isBlank())
                ? Optional.empty() : organismoFinRepository.findFirstByCodOfOrderByGestionDesc(a.getOrgFin().trim());

        CalculoActivoDto c = calculoService.calcular(a, g, fecha, ui, uc);
        return new ActivoFijoFichaDto(
                a.getEntidad(), a.getUnidad(), a.getCodigo(), a.getCodigoSec(),
                a.getDia(), a.getMes(), a.getAno(), ui,
                a.getDescrip(),
                a.getCodCont(), g == null ? null : g.getNombre(),
                a.getCodAux(), x == null ? null : x.getNomAux(),
                a.getCodOfic(), of == null ? null : of.getNomOfic(),
                a.getCodResp(), r == null ? null : r.getNomResp(),
                r == null ? null : r.getCargo(),
                a.getCodEstado(), es == null ? null : es.getNomEstado(),
                a.getObserv(), a.getCodRube(),
                org.map(OrganismoFin::getDescripcion).orElse(a.getOrgFin()),
                a.getNroConv(),
                g == null ? null : g.getDepreciar(),
                g == null ? null : g.getActualizar(),
                Integer.valueOf(1).equals(a.getApiEstado()) ? "APROBADO" : "ELABORADO",
                c);
    }

    private static String recortar(String s, int max) {
        if (s == null) return null;
        return s.length() > max ? s.substring(0, max) : s;
    }

    private static boolean contiene(String valor, String filtro) {
        if (filtro == null || filtro.isBlank()) return true;
        return valor != null && valor.toLowerCase().contains(filtro.toLowerCase());
    }

    private static ActivoFijo noHay(Optional<ActivoFijo> o) {
        return o.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay mas activos en esa direccion"));
    }
}
