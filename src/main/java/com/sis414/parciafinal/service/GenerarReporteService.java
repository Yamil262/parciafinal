package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.CalculoActivoDto;
import com.sis414.parciafinal.dto.FilaReporteDto;
import com.sis414.parciafinal.dto.ReporteGenerarRequest;
import com.sis414.parciafinal.dto.ReporteGeneradoDto;
import com.sis414.parciafinal.entity.ActivoFijo;
import com.sis414.parciafinal.repository.ActivoFijoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Pantalla REPORTES A MEDIDA: filtros (Todos = null) y calculo a una fecha. */
@Service
public class GenerarReporteService {

    private final ActivoFijoRepository activoRepository;
    private final CalculoActivoService calculoService;

    public GenerarReporteService(ActivoFijoRepository activoRepository, CalculoActivoService calculoService) {
        this.activoRepository = activoRepository;
        this.calculoService = calculoService;
    }

    @Transactional(readOnly = true)
    public ReporteGeneradoDto generar(ReporteGenerarRequest r) {
        LocalDate fecha = calculoService.fecha(r.dia(), r.mes(), r.ano());
        BigDecimal ufv = r.ufv() == null ? BigDecimal.ONE : r.ufv();

        List<FilaReporteDto> filas = new ArrayList<>();
        BigDecimal tCosto = BigDecimal.ZERO, tActual = BigDecimal.ZERO, tDep = BigDecimal.ZERO, tNeto = BigDecimal.ZERO;

        for (ActivoFijo a : activoRepository.findAll()) {
            if (!ok(r.unidadId(), a.getUnidad() == null ? null : a.getUnidad().getId())) continue;
            if (!ok(r.grupoId(), a.getGrupo() == null ? null : a.getGrupo().getId())) continue;
            if (!ok(r.auxiliarId(), a.getAuxiliar() == null ? null : a.getAuxiliar().getId())) continue;
            if (!ok(r.activoId(), a.getId())) continue;
            if (!ok(r.oficinaId(), a.getOficina() == null ? null : a.getOficina().getId())) continue;
            if (!ok(r.responsableId(), a.getResponsable() == null ? null : a.getResponsable().getId())) continue;
            if (r.anoInicial() != null && (a.getAno() == null || a.getAno() < r.anoInicial())) continue;
            if (r.anoFinal() != null && (a.getAno() == null || a.getAno() > r.anoFinal())) continue;

            CalculoActivoDto c = calculoService.calcular(a, fecha, ufv, ufv);
            filas.add(new FilaReporteDto(a.getId(), a.getCodigo(), a.getDescrip(),
                    a.getGrupo() == null ? null : a.getGrupo().getNombre(),
                    a.getAuxiliar() == null ? null : a.getAuxiliar().getNomAux(),
                    a.getOficina() == null ? null : a.getOficina().getNomOfic(),
                    a.getResponsable() == null ? null : a.getResponsable().getNomResp(),
                    c.costoInicial(), c.factorActual(), c.valorActual(), c.depGestion(), c.depAcumulada(), c.valorNeto()));
            tCosto = tCosto.add(c.costoInicial());
            tActual = tActual.add(c.valorActual());
            tDep = tDep.add(c.depAcumulada());
            tNeto = tNeto.add(c.valorNeto());
        }
        return new ReporteGeneradoDto(calculoService.fechaDto(fecha), ufv, filas.size(), tCosto, tActual, tDep, tNeto, filas);
    }

    private static boolean ok(Long filtro, Long valor) {
        return filtro == null || filtro.equals(valor);
    }
}
