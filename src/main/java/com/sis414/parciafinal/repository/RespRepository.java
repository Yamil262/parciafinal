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
public interface RespRepository extends JpaRepository<Resp, RespId> {

    /** Registros no anulados (api_estado distinto de 3). */
    @Query("select e from Resp e where e.apiEstado is null or e.apiEstado <> 3")
    Page<Resp> listarActivos(Pageable pageable);

    @Query("select e from Resp e where e.entidad = :entidad and e.unidad = :unidad and e.codOfic = :codOfic and (e.apiEstado is null or e.apiEstado <> 3)")
    List<Resp> buscarPorOficina(@Param("entidad") String entidad, @Param("unidad") String unidad, @Param("codOfic") Integer codOfic);

}
