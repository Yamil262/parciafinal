package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, OficinaId> {

    /** Registros no anulados (api_estado distinto de 3). */
    @Query("select e from Oficina e where e.apiEstado is null or e.apiEstado <> 3")
    Page<Oficina> listarActivos(Pageable pageable);

    boolean existsByEntidadAndUnidad(String entidad, String unidad);

    @Query("select e from Oficina e where e.entidad = :entidad and e.unidad = :unidad and (e.apiEstado is null or e.apiEstado <> 3)")
    List<Oficina> buscarPorUnidad(@Param("entidad") String entidad, @Param("unidad") String unidad);

}
