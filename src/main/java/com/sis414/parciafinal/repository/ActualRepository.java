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
public interface ActualRepository extends JpaRepository<Actual, ActualId> {

    /** Registros no anulados (api_estado distinto de 3). */
    @Query("select e from Actual e where e.apiEstado is null or e.apiEstado <> 3")
    Page<Actual> listarActivos(Pageable pageable);

    @Query("select e from Actual e where e.entidad = :entidad and e.unidad = :unidad and (e.apiEstado is null or e.apiEstado <> 3)")
    List<Actual> buscarPorUnidad(@Param("entidad") String entidad, @Param("unidad") String unidad);

    @Query("select e from Actual e where e.entidad = :entidad and e.unidad = :unidad and e.codOfic = :codOfic and (e.apiEstado is null or e.apiEstado <> 3)")
    List<Actual> buscarPorOficina(@Param("entidad") String entidad, @Param("unidad") String unidad, @Param("codOfic") Integer codOfic);

    boolean existsByCodCont(Integer codCont);

    boolean existsByCodEstado(Integer codEstado);

}
