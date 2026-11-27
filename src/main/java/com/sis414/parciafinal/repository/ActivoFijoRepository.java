package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.ActivoFijo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivoFijoRepository extends JpaRepository<ActivoFijo, ActivoFijo.Key> {
    List<ActivoFijo> findByEntidadAndUnidadOrderByCodigoAsc(String entidad, String unidad);
    Optional<ActivoFijo> findFirstByEntidadAndUnidadOrderByCodigoAsc(String entidad, String unidad);
    Optional<ActivoFijo> findFirstByEntidadAndUnidadOrderByCodigoDesc(String entidad, String unidad);
    Optional<ActivoFijo> findFirstByEntidadAndUnidadAndCodigoGreaterThanOrderByCodigoAsc(String entidad, String unidad, String codigo);
    Optional<ActivoFijo> findFirstByEntidadAndUnidadAndCodigoLessThanOrderByCodigoDesc(String entidad, String unidad, String codigo);
}
