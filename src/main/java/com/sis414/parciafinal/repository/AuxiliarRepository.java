package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.Auxiliar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuxiliarRepository extends JpaRepository<Auxiliar, Auxiliar.Key> {
    List<Auxiliar> findByCodCont(Integer codCont);
}
