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
public interface RevalRepository extends JpaRepository<Reval, Long> {

    @Query("select e from Reval e where e.entidad = :entidad and e.unidad = :unidad and e.codigo = :codigo")
    List<Reval> buscarPorActivo(@Param("entidad") String entidad, @Param("unidad") String unidad, @Param("codigo") String codigo);

}
