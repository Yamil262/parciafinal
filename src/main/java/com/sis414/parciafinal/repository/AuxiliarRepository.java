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
public interface AuxiliarRepository extends JpaRepository<Auxiliar, AuxiliarId> {

    boolean existsByEntidadAndUnidad(String entidad, String unidad);

    @Query("select e from Auxiliar e where e.codCont = :codCont")
    List<Auxiliar> buscarPorCuenta(@Param("codCont") Integer codCont);

    boolean existsByCodCont(Integer codCont);

}
