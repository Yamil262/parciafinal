package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.Mes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesRepository extends JpaRepository<Mes, Integer> {
}
