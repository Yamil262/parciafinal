# parciafinal

API REST de **activos fijos (V.S.I.A.F.)** con **Spring Boot 3.4 · Java 21 · Gradle · PostgreSQL**, sobre los datos de los archivos DBF del sistema original (`dbfs.zip`).

- Dependencias: Spring Web, Spring Data JPA, Validation, springdoc-openapi (Swagger UI) y PostgreSQL (H2 solo en pruebas).
- Capas: `entity` → `repository` → `service` → `controller`, con `dto` (Request/Response) entre el cliente y las entidades.
- Paquete base: `com.sis414.parciafinal`

## Ejecutar

1. Crear la base `parciafinal` en PostgreSQL. Si ya existían tablas de una versión anterior, borrarlas (o recrear la base).
2. Variables opcionales: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` (por defecto `jdbc:postgresql://localhost:5432/parciafinal`, `postgres`/`postgres`).
3. Ejecutar `ParciafinalApplication` (o `./gradlew bootRun`).
4. Swagger UI: <http://localhost:8080/swagger-ui/index.html>

Al primer arranque Hibernate crea las tablas y `SeedDataLoader` carga `src/main/resources/seed/seed.sql` **solo si la base está vacía**.

## Cómo se relacionan las tablas

```
UnidadAdmin (entidad, unidad)
 ├─< Oficina (entidad, unidad, codOfic)
 │    ├─< Resp (entidad, unidad, codOfic, codResp)
 │    └─< Actual            (activo asignado a oficina)
 ├─< Auxiliar (entidad, unidad, codCont, codAux) >─ CodCont
 └─< Actual (entidad, unidad, codigo)
        ├─ Auxiliar · CodCont · Oficina · Resp · Estado
        ├─< Reval        (revaluaciones del activo)
        └─< Trasfe       (transferencias del activo)

CodCont ─< CtaPar >─ ObjGasto (partida)      EstEnt ─< Entidades
MotivoBaja ─< ActivoBaja                      Estado ─< Actual
Catálogos sueltos: Mes, ClaDepts (departamentos), OrganismoFin, Config, Usuario (passw)
```

- Las claves son **las naturales del DBF** (`@IdClass` compuestas): las URLs llevan los campos de la clave, por ejemplo `/api/oficinas/{entidad}/{unidad}/{codOfic}`. No hay ids artificiales salvo en `Config`, `ActivoBaja`, `Reval` y `Trasfe`, que en el DBF no tienen clave única.
- Las relaciones `@ManyToOne` son de **solo lectura y sin restricción FK**; sirven para mostrar nombres (`unidadDescrip`, `oficinaNombre`). La integridad se valida en los `service`: al crear o modificar se comprueba que existan los registros padre, y al eliminar que no haya hijos.

## Endpoints

Todos tienen: `GET` (paginado `?page=0&size=20&sort=campo,asc`), `GET /{clave}`, `POST`, `PUT /{clave}` y `DELETE /{clave}`.

| Ruta | Tabla DBF | Clave | Registros cargados |
|---|---|---|---|
| `/api/unidades` | `unidadadmin` | entidad, unidad | 0 (se crean por API) |
| `/api/oficinas` | `oficina` | entidad, unidad, codOfic | 0 |
| `/api/responsables` | `resp` | entidad, unidad, codOfic, codResp | 0 |
| `/api/auxiliares` | `auxiliar` | entidad, unidad, codCont, codAux | 0 |
| `/api/activos` | `actual` | entidad, unidad, codigo | 0 |
| `/api/cuentas-contables` | `codcont` | codCont | 39 |
| `/api/estados` | `estado` | codEstado | 3 |
| `/api/meses` | `mes` | mes | 12 |
| `/api/motivos-baja` | `baja` | codBaja | 9 |
| `/api/activos-baja` | `bajas` | id autogenerado | 0 |
| `/api/revaluaciones` | `reval` | id autogenerado | 0 |
| `/api/transferencias` | `trasfe` | id autogenerado | 0 |
| `/api/cuentas-partidas` | `cta_par` | codCont, partida, gestion | 273 |
| `/api/partidas` | `objgasto` | gestion, partida | 98 |
| `/api/organismos-financiadores` | `organismo_fin` | gestion, codOf | 142 |
| `/api/estructuras-entidad` | `est_ent` | gestion, sectorEnt, subsecEnt, areaEnt, subAreaEnt, nivelInst | 108 |
| `/api/entidades` | `entidades` | gestion, entidad | 642 |
| `/api/departamentos` | `cla_depts` | codigo | 10 |
| `/api/configuracion` | `config` | id autogenerado | 0 |
| `/api/usuarios` | `passw` | usuario | 1 |

Endpoints anidados:

- `GET /api/unidades/{entidad}/{unidad}/oficinas` y `/activos`
- `GET /api/oficinas/{entidad}/{unidad}/{codOfic}/responsables` y `/activos`
- `GET /api/activos/{entidad}/{unidad}/{codigo}/revaluaciones` y `/transferencias`
- `GET /api/cuentas-contables/{codCont}/auxiliares`

## Datos sensibles

- **Usuarios**: la respuesta nunca incluye la contraseña. En `POST/PUT` es solo de entrada y se guarda con hash PBKDF2. El hash original del `admin` se conservó como `LEGACY:<hex>`; no es verificable y no se expone.
- **Responsables**: el CI se devuelve enmascarado (`ciEnmascarado`, solo los últimos 3 dígitos). El CI completo solo se recibe al crear o modificar.
- Los campos de auditoría (`feult`, `usuar`, `fecMod`, `usuMod`, `apiEstado`) los maneja el servidor.

## Reglas respetadas del DBF

- **Longitudes** de texto (`@Size`), **precisión decimal** (`@Digits`, por ejemplo `COSTO N15.2`) y números no negativos.
- **Campos obligatorios**: las claves y los campos descriptivos principales.
- **Baja lógica** en `oficina`, `resp` y `actual`: `DELETE` pone `api_estado = 3` (anulado) y el listado los oculta salvo `?incluirAnulados=true`.
- `FEULT/USUAR` se actualizan en cada alta o cambio (`usuar` por defecto `api`).
- Errores en JSON (ProblemDetail): 400 validación o referencia inexistente, 404 no encontrado, 409 duplicado o con dependientes.

## Orden para cargar datos propios

Como las tablas operativas (unidades, oficinas, responsables, auxiliares, activos) vienen vacías en los DBF, se crean en este orden por la API:

1. `POST /api/unidades` → `{ "entidad": "143", "unidad": "SIS", "descrip": "CARRERA DE INGENIERIA DE SISTEMAS", "ciudad": "Potosi", "estadoUni": 1 }`
2. `POST /api/oficinas` → `{ "entidad": "143", "unidad": "SIS", "codOfic": 1, "nomOfic": "DIRECCION" }`
3. `POST /api/responsables` → `{ "entidad": "143", "unidad": "SIS", "codOfic": 1, "codResp": 1, "nomResp": "JUAN PEREZ", "cargo": "DIRECTOR", "ci": "12345678" }`
4. `POST /api/auxiliares` (opcional) y luego `POST /api/activos`.

## Pruebas

`./gradlew test` ejecuta pruebas con MockMvc y H2 en memoria: catálogos cargados, 404, validación, y que usuarios y responsables no expongan datos sensibles.
