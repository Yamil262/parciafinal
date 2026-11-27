package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.CalculoActivoDto;
import com.sis414.parciafinal.dto.FilaReporteDto;
import com.sis414.parciafinal.dto.ReporteGenerarRequest;
import com.sis414.parciafinal.dto.ReporteGeneradoDto;
import com.sis414.parciafinal.entity.ActivoFijo;
import com.sis414.parciafinal.entity.Auxiliar;
import com.sis414.parciafinal.entity.Grupo;
import com.sis414.parciafinal.entity.Oficina;
import com.sis414.parciafinal.entity.Responsable;
import com.sis414.parciafinal.repository.ActivoFijoRepository;
import com.sis414.parciafinal.repository.AuxiliarRepository;
import com.sis414.parciafinal.repository.GrupoRepository;
import com.sis414.parciafinal.repository.OficinaRepository;
import com.sis414.parciafinal.repository.ResponsableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** Pantalla REPORTES A MEDIDA: filtros (Todos = null) y cálculo a una fecha. */
@Service
public class GenerarReporteService {

    private final ActivoFijoRepository activoRepository;
    private final GrupoRepository grupoRepository;
    private final AuxiliarRepository auxiliarRepository;
    private final OficinaRepository oficinaRepository;
    private final ResponsableRepository responsableRepository;
    private final CalculoActivoService calculoService;

    public GenerarReporteService(ActivoFijoRepository activoRepository, GrupoRepository grupoRepository,
                                 AuxiliarRepository auxiliarRepository, OficinaRepository oficinaRepository,
                                 ResponsableRepository responsableRepository, CalculoActivoService calculoService) {
        this.activoRepository = activoRepository;
        this.grupoRepository = grupoRepository;
        this.auxiliarRepository = auxiliarRepository;
        this.oficinaRepository = oficinaRepository;
        this.responsableRepository = responsableRepository;
        this.calculoService = calculoService;
    }

    @Transactional(readOnly = true)
    public ReporteGeneradoDto generar(ReporteGenerarRequest r) {
        LocalDate fecha = calculoService.fecha(r.dia(), r.mes(), r.ano());
        BigDecimal ufv = r.ufv() == null ? BigDecimal.ONE : r.ufv();

        Map<Integer, Grupo> grupos = new HashMap<>();
        for (Grupo g : grupoRepository.findAll()) grupos.put(g.getCodCont(), g);
        Map<String, Auxiliar> auxiliares = new HashMap<>();
        for (Auxiliar x : auxiliarRepository.findAll())
            auxiliares.put(x.getEntidad() + "|" + x.getUnidad() + "|" + x.getCodCont() + "|" + x.getCodAux(), x);
        Map<String, Oficina> oficinas = new HashMap<>();
        for (Oficina o : oficinaRepository.findAll())
            oficinas.put(o.getEntidad() + "|" + o.getUnidad() + "|" + o.getCodOfic(), o);
        Map<String, Responsable> responsables = new HashMap<>();
        for (Responsable p : responsableRepository.findAll())
            responsables.put(p.getEntidad() + "|" + p.getUnidad() + "|" + p.getCodOfic() + "|" + p.getCodResp(), p);

        List<FilaReporteDto> filas = new ArrayList<>();
        BigDecimal tCosto = BigDecimal.ZERO, tActual = BigDecimal.ZERO, tDep = BigDecimal.ZERO, tNeto = BigDecimal.ZERO;

        for (ActivoFijo a : activoRepository.findAll()) {
            if (!ok(r.entidad(), a.getEntidad())) continue;
            if (!ok(r.unidad(), a.getUnidad())) continue;
            if (!ok(r.codCont(), a.getCodCont())) continue;
            if (!ok(r.codAux(), a.getCodAux())) continue;
            if (!ok(r.codigo(), a.getCodigo())) continue;
            if (!ok(r.codOfic(), a.getCodOfic())) continue;
            if (!ok(r.codResp(), a.getCodResp())) continue;
            if (r.anoInicial() != null && (a.getAno() == null || a.getAno() < r.anoInicial())) continue;
            if (r.anoFinal() != null && (a.getAno() == null || a.getAno() > r.anoFinal())) continue;

            Grupo g = grupos.get(a.getCodCont());
            Auxiliar x = auxiliares.get(a.getEntidad() + "|" + a.getUnidad() + "|" + a.getCodCont() + "|" + a.getCodAux());
            Oficina of = oficinas.get(a.getEntidad() + "|" + a.getUnidad() + "|" + a.getCodOfic());
            Responsable p = responsables.get(a.getEntidad() + "|" + a.getUnidad() + "|" + a.getCodOfic() + "|" + a.getCodResp());

            CalculoActivoDto c = calculoService.calcular(a, g, fecha, ufv, ufv);
            filas.add(new FilaReporteDto(a.getEntidad(), a.getUnidad(), a.getCodigo(), a.getDescrip(),
                    g == null ? null : g.getNombre(),
                    x == null ? null : x.getNomAux(),
                    of == null ? null : of.getNomOfic(),
                    p == null ? null : p.getNomResp(),
                    c.costoInicial(), c.factorActual(), c.valorActual(), c.depGestion(), c.depAcumulada(), c.valorNeto()));
            tCosto = tCosto.add(c.costoInicial());
            tActual = tActual.add(c.valorActual());
            tDep = tDep.add(c.depAcumulada());
            tNeto = tNeto.add(c.valorNeto());
        }
        return new ReporteGeneradoDto(calculoService.fechaDto(fecha), ufv, filas.size(), tCosto, tActual, tDep, tNeto, filas);
    }

    private static boolean ok(Object filtro, Object valor) {
        return filtro == null || Objects.equals(filtro, valor);
    }
}
