package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.CalculoActivoDto;
import com.sis414.parciafinal.dto.FechaCalculoDto;
import com.sis414.parciafinal.entity.ActivoFijo;
import com.sis414.parciafinal.entity.Grupo;
import com.sis414.parciafinal.entity.Mes;
import com.sis414.parciafinal.repository.GrupoRepository;
import com.sis414.parciafinal.repository.MesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * Cálculos de la pantalla Activos Fijos / Reportes.
 * % deprecia = 100 / vida; dep. gestión = valor actual x % x días / 365; valor neto = valor actual - dep. acumulada.
 * No existe tabla de índices UFV entre las 24 tablas, por eso las UFV llegan como parámetro.
 */
@Service
public class CalculoActivoService {

    private final MesRepository mesRepository;
    private final GrupoRepository grupoRepository;

    public CalculoActivoService(MesRepository mesRepository, GrupoRepository grupoRepository) {
        this.mesRepository = mesRepository;
        this.grupoRepository = grupoRepository;
    }

    public LocalDate fecha(Integer dia, Integer mes, Integer ano) {
        if (dia == null || mes == null || ano == null) return LocalDate.now();
        try {
            return LocalDate.of(ano, mes, dia);
        } catch (DateTimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fecha de calculo invalida");
        }
    }

    public FechaCalculoDto hoy() {
        return fechaDto(LocalDate.now());
    }

    public FechaCalculoDto fechaDto(LocalDate f) {
        String nombre = mesRepository.findById(f.getMonthValue())
                .map(Mes::getNomMes)
                .orElse(f.getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es")).toUpperCase());
        return new FechaCalculoDto(f.getDayOfMonth(), f.getMonthValue(), nombre, f.getYear());
    }

    public CalculoActivoDto calcular(ActivoFijo a, LocalDate fecha, BigDecimal ufvInc, BigDecimal ufvCalc) {
        Grupo g = a.getCodCont() == null ? null : grupoRepository.findById(a.getCodCont()).orElse(null);
        return calcular(a, g, fecha, ufvInc, ufvCalc);
    }

    public CalculoActivoDto calcular(ActivoFijo a, Grupo g, LocalDate fecha, BigDecimal ufvInc, BigDecimal ufvCalc) {
        BigDecimal costo = nz(a.getCosto());
        BigDecimal depIni = nz(a.getDepacu());
        boolean actualizar = g == null || !Boolean.FALSE.equals(g.getActualizar());
        boolean depreciar = g == null || !Boolean.FALSE.equals(g.getDepreciar());

        BigDecimal factor = (actualizar && ufvInc.signum() > 0)
                ? ufvCalc.divide(ufvInc, 6, RoundingMode.HALF_UP)
                : BigDecimal.ONE.setScale(6);
        BigDecimal valorActual = costo.multiply(factor).setScale(2, RoundingMode.HALF_UP);

        BigDecimal vida = nz(a.getVidaUtil());
        BigDecimal pct = vida.signum() > 0
                ? BigDecimal.valueOf(100).divide(vida, 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(2);

        LocalDate inc = incorporacion(a, fecha);
        long dias = fecha.isBefore(inc) ? 0 : ChronoUnit.DAYS.between(inc, fecha) + 1;

        LocalDate inicioAnio = LocalDate.of(fecha.getYear(), 1, 1);
        LocalDate ini = inc.isAfter(inicioAnio) ? inc : inicioAnio;
        long diasGestion = fecha.isBefore(ini) ? 0 : ChronoUnit.DAYS.between(ini, fecha) + 1;

        BigDecimal depGestion = depreciar
                ? valorActual.multiply(pct).multiply(BigDecimal.valueOf(diasGestion))
                        .divide(BigDecimal.valueOf(36500), 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(2);
        BigDecimal depAcum = depIni.add(depGestion);
        if (depAcum.compareTo(valorActual) > 0) depAcum = valorActual;
        BigDecimal neto = valorActual.subtract(depAcum);

        return new CalculoActivoDto(costo, depIni, factor, valorActual, vida, (int) dias, pct,
                depGestion, depAcum, neto, fechaDto(fecha), ufvCalc);
    }

    private LocalDate incorporacion(ActivoFijo a, LocalDate porDefecto) {
        if (a.getAno() == null || a.getMes() == null || a.getDia() == null) return porDefecto;
        try {
            return LocalDate.of(a.getAno(), a.getMes(), a.getDia());
        } catch (DateTimeException e) {
            return porDefecto;
        }
    }

    private static BigDecimal nz(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }
}
