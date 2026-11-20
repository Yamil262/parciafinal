package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.Mes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesRepository extends JpaRepository<Mes, Long> {
    List<Mes> findByMes(Integer mes);
}
