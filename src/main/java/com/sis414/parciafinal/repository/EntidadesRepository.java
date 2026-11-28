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
public interface EntidadesRepository extends JpaRepository<Entidades, EntidadesId> {

    boolean existsByGestionAndSectorEntAndSubsecEntAndAreaEntAndSubAreaEntAndNivelInst(Integer gestion, Integer sectorEnt, Integer subsecEnt, Integer areaEnt, Integer subAreaEnt, Integer nivelInst);

}
