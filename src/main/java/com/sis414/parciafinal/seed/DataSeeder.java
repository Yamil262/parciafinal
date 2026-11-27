package com.sis414.parciafinal.seed;

import com.sis414.parciafinal.entity.*;
import com.sis414.parciafinal.repository.*;
import com.sis414.parciafinal.service.ConfiguracionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Carga al arrancar los datos que traen los DBF del VSIAF (carpeta resources/seed, generada con tools/dbf_to_tsv.py).
 * Cada tabla se carga solo si está vacía, así que es seguro reiniciar la aplicación.
 * Con vsiaf.seed.demo=true también crea la unidad 143/SIS y unos pocos activos de ejemplo (los DBF de oficina, resp y actual vienen vacíos).
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);
    private static final String ENT = "143";
    private static final String UNI = "SIS";

    private final MesRepository mes;
    private final EstadoRepository estado;
    private final MotivoBajaRepository motivoBaja;
    private final DepartamentoRepository departamento;
    private final GrupoRepository grupo;
    private final CuentaPartidaRepository cuentaPartida;
    private final ObjetoGastoRepository objetoGasto;
    private final OrganismoFinRepository organismoFin;
    private final EstructuraEntidadRepository estructura;
    private final EntidadRepository entidad;
    private final ReporteRepository reporte;
    private final OrgUserRepository orgUser;
    private final UsuarioRepository usuario;
    private final UnidadAdminRepository unidadAdmin;
    private final ConfigRepository config;
    private final OficinaRepository oficina;
    private final ResponsableRepository responsable;
    private final AuxiliarRepository auxiliar;
    private final ActivoFijoRepository activo;
    private final ConfiguracionService configuracion;

    @Value("${vsiaf.seed.enabled:true}")
    private boolean enabled;

    @Value("${vsiaf.seed.demo:true}")
    private boolean demo;

    public DataSeeder(MesRepository mes, EstadoRepository estado, MotivoBajaRepository motivoBaja,
                      DepartamentoRepository departamento, GrupoRepository grupo, CuentaPartidaRepository cuentaPartida,
                      ObjetoGastoRepository objetoGasto, OrganismoFinRepository organismoFin,
                      EstructuraEntidadRepository estructura, EntidadRepository entidad, ReporteRepository reporte,
                      OrgUserRepository orgUser, UsuarioRepository usuario, UnidadAdminRepository unidadAdmin,
                      ConfigRepository config, OficinaRepository oficina, ResponsableRepository responsable,
                      AuxiliarRepository auxiliar, ActivoFijoRepository activo, ConfiguracionService configuracion) {
        this.mes = mes;
        this.estado = estado;
        this.motivoBaja = motivoBaja;
        this.departamento = departamento;
        this.grupo = grupo;
        this.cuentaPartida = cuentaPartida;
        this.objetoGasto = objetoGasto;
        this.organismoFin = organismoFin;
        this.estructura = estructura;
        this.entidad = entidad;
        this.reporte = reporte;
        this.orgUser = orgUser;
        this.usuario = usuario;
        this.unidadAdmin = unidadAdmin;
        this.config = config;
        this.oficina = oficina;
        this.responsable = responsable;
        this.auxiliar = auxiliar;
        this.activo = activo;
        this.configuracion = configuracion;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!enabled) return;
        cargar("mes.tsv", mes, c -> {
            Mes e = new Mes();
            e.setMes(i(c[0]));
            e.setNomMes(c[1]);
            return e;
        });
        cargar("estado.tsv", estado, c -> {
            Estado e = new Estado();
            e.setCodEstado(i(c[0]));
            e.setNomEstado(c[1]);
            return e;
        });
        cargar("motivo_baja.tsv", motivoBaja, c -> {
            MotivoBaja e = new MotivoBaja();
            e.setCodBaja(i(c[0]));
            e.setDescBaja(c[1]);
            return e;
        });
        cargar("departamento.tsv", departamento, c -> {
            Departamento e = new Departamento();
            e.setCodigo(i(c[0]));
            e.setDescripcion(c[1]);
            e.setSigla(c[2]);
            return e;
        });
        cargar("grupo.tsv", grupo, c -> {
            Grupo e = new Grupo();
            e.setCodCont(i(c[0]));
            e.setNombre(c[1]);
            e.setVidaUtil(i(c[2]));
            e.setDepreciar(b(c[3]));
            e.setActualizar(b(c[4]));
            return e;
        });
        cargar("cuenta_partida.tsv", cuentaPartida, c -> {
            CuentaPartida e = new CuentaPartida();
            e.setCodCont(i(c[0]));
            e.setPartida(c[1]);
            e.setGestion(i(c[2]));
            return e;
        });
        cargar("objeto_gasto.tsv", objetoGasto, c -> {
            ObjetoGasto e = new ObjetoGasto();
            e.setGestion(i(c[0]));
            e.setPartida(c[1]);
            e.setDescrip(c[2]);
            return e;
        });
        cargar("organismo_fin.tsv", organismoFin, c -> {
            OrganismoFin e = new OrganismoFin();
            e.setGestion(i(c[0]));
            e.setCodOf(c[1]);
            e.setDescripcion(c[2]);
            e.setSigla(c[3]);
            return e;
        });
        cargar("estructura_entidad.tsv", estructura, c -> {
            EstructuraEntidad e = new EstructuraEntidad();
            e.setGestion(i(c[0]));
            e.setSectorEnt(i(c[1]));
            e.setSubsecEnt(i(c[2]));
            e.setAreaEnt(i(c[3]));
            e.setSubareaEnt(i(c[4]));
            e.setNivelInst(i(c[5]));
            e.setDesEstruc(c[6]);
            e.setSiglaEstru(c[7]);
            e.setApropiable(c[8]);
            return e;
        });
        cargar("entidad.tsv", entidad, c -> {
            Entidad e = new Entidad();
            e.setGestion(i(c[0]));
            e.setEntidad(c[1]);
            e.setDescEnt(c[2]);
            e.setSiglaEnt(c[3]);
            e.setSectorEnt(i(c[4]));
            e.setSubsecEnt(i(c[5]));
            e.setAreaEnt(i(c[6]));
            e.setSubareaEnt(i(c[7]));
            e.setNivelInst(i(c[8]));
            return e;
        });
        cargar("reporte.tsv", reporte, c -> {
            Reporte e = new Reporte();
            e.setReportName(c[0]);
            e.setContents(c[1]);
            e.setModType(c[2]);
            return e;
        });
        cargar("org_user.tsv", orgUser, c -> {
            OrgUser e = new OrgUser();
            e.setType(c[0]);
            e.setIdUser(c[1]);
            e.setName(c[2]);
            e.setReadonly(b(c[3]));
            e.setCkval(i(c[4]));
            e.setData(c[5]);
            e.setUpdated(d(c[6]));
            return e;
        });
        if (usuario.count() == 0) {
            // PASSW.DBF trae un solo usuario: admin (la clave original está cifrada; aquí queda "admin").
            Usuario u = new Usuario();
            u.setUsuario("admin");
            u.setCompleto("administrador");
            u.setDes("");
            u.setPsw("admin");
            u.setTipo(true);
            u.setGrupo("Admin");
            u.setFeult(LocalDate.of(2011, 8, 22));
            u.setUsuar("admin");
            usuario.save(u);
            log.info("Seed: usuario admin creado");
        }
        if (demo) sembrarDemo();
    }

    private void sembrarDemo() {
        if (unidadAdmin.count() == 0) {
            UnidadAdmin u = new UnidadAdmin();
            u.setEntidad(ENT);
            u.setUnidad(UNI);
            u.setDescrip("CARRERA DE INGENIERIA DE SISTEMAS");
            u.setCiudad("POTOSI");
            u.setEstadoUni(1);
            unidadAdmin.save(u);

            Config c = new Config();
            c.setEntidad(ENT);
            c.setUnidad(UNI);
            c.setDescEnt("Universidad Autónoma Tomás Frías");
            c.setDescUni("CARRERA DE INGENIERIA DE SISTEMAS");
            c.setUsuario("admin");
            config.save(c);

            configuracion.seleccionarUnidad(ENT, UNI);
            log.info("Seed demo: unidad {}/{}", ENT, UNI);
        }
        if (oficina.count() == 0) {
            oficina(1, "DIRECCION DE CARRERA");
            oficina(2, "LABORATORIO DE COMPUTACION");
            oficina(3, "SECRETARIA");
            responsable(1, 1, "JUAN PEREZ MAMANI", "DIRECTOR DE CARRERA", "1234567");
            responsable(2, 1, "MARIA LOPEZ QUISPE", "ENCARGADA DE LABORATORIO", "7654321");
            responsable(3, 1, "CARLOS ROJAS CRUZ", "SECRETARIO", "4567890");
        }
        if (auxiliar.count() == 0) {
            auxiliar(15, 1, "COMPUTADORAS DE ESCRITORIO");
            auxiliar(15, 2, "IMPRESORAS");
            auxiliar(2, 1, "ESCRITORIOS Y SILLAS");
        }
        if (activo.count() == 0) {
            activo("SIS-00001", 15, 1, 4, "COMPUTADORA DELL OPTIPLEX 7090", "5200.00", 2024, 3, 15, 2, 1);
            activo("SIS-00002", 15, 1, 4, "COMPUTADORA HP PRODESK 400 G7", "4800.00", 2024, 3, 20, 2, 1);
            activo("SIS-00003", 15, 2, 4, "IMPRESORA EPSON L3250", "1850.50", 2024, 5, 10, 1, 1);
            activo("SIS-00004", 2, 1, 10, "ESCRITORIO METALICO 6 CAJONES", "1200.00", 2023, 8, 1, 1, 1);
            activo("SIS-00005", 2, 1, 10, "SILLA EJECUTIVA GIRATORIA", "650.00", 2023, 8, 1, 1, 1);
            activo("SIS-00006", 6, null, 8, "PROYECTOR MULTIMEDIA EPSON X49", "3400.00", 2022, 11, 14, 2, 1);
            log.info("Seed demo: oficinas, responsables, auxiliares y 6 activos de ejemplo");
        }
    }

    private void oficina(int cod, String nombre) {
        Oficina o = new Oficina();
        o.setEntidad(ENT);
        o.setUnidad(UNI);
        o.setCodOfic(cod);
        o.setNomOfic(nombre);
        o.setApiEstado(1);
        o.setFeult(LocalDate.now());
        o.setUsuar("admin");
        oficina.save(o);
    }

    private void responsable(int codOfic, int codResp, String nombre, String cargo, String ci) {
        Responsable r = new Responsable();
        r.setEntidad(ENT);
        r.setUnidad(UNI);
        r.setCodOfic(codOfic);
        r.setCodResp(codResp);
        r.setNomResp(nombre);
        r.setCargo(cargo);
        r.setCi(ci);
        r.setApiEstado(1);
        r.setFeult(LocalDate.now());
        r.setUsuar("admin");
        responsable.save(r);
    }

    private void auxiliar(int codCont, int codAux, String nombre) {
        Auxiliar a = new Auxiliar();
        a.setEntidad(ENT);
        a.setUnidad(UNI);
        a.setCodCont(codCont);
        a.setCodAux(codAux);
        a.setNomAux(nombre);
        a.setFeult(LocalDate.now());
        a.setUsuar("admin");
        auxiliar.save(a);
    }

    private void activo(String codigo, int codCont, Integer codAux, int vida, String descrip, String costo,
                        int ano, int mes, int dia, int codOfic, int codResp) {
        ActivoFijo a = new ActivoFijo();
        a.setEntidad(ENT);
        a.setUnidad(UNI);
        a.setCodigo(codigo);
        a.setCodCont(codCont);
        a.setCodAux(codAux);
        a.setVidaUtil(new BigDecimal(vida));
        a.setDescrip(descrip);
        a.setCosto(new BigDecimal(costo));
        a.setDepacu(BigDecimal.ZERO);
        a.setAno(ano);
        a.setMes(mes);
        a.setDia(dia);
        a.setBrev(false);
        a.setBandUfv(false);
        a.setCodOfic(codOfic);
        a.setCodResp(codResp);
        a.setCodEstado(1);
        a.setApiEstado(0);
        a.setFeult(LocalDate.now());
        a.setUsuar("admin");
        activo.save(a);
    }

    // ---------- utilidades ----------

    private <T> void cargar(String archivo, JpaRepository<T, ?> repo, Function<String[], T> mapa) throws IOException {
        if (repo.count() > 0) return;
        List<T> lista = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(
                new ClassPathResource("seed/" + archivo).getInputStream(), StandardCharsets.UTF_8))) {
            r.readLine(); // encabezado
            String linea;
            while ((linea = r.readLine()) != null) {
                if (linea.isEmpty()) continue;
                lista.add(mapa.apply(linea.split("\t", -1)));
            }
        }
        repo.saveAll(lista);
        log.info("Seed: {} filas desde {}", lista.size(), archivo);
    }

    private static Integer i(String s) {
        return s == null || s.isBlank() ? null : Integer.valueOf(s.trim());
    }

    private static Boolean b(String s) {
        return "1".equals(s);
    }

    private static LocalDate d(String s) {
        return s == null || s.isBlank() ? null : LocalDate.parse(s.trim());
    }
}
