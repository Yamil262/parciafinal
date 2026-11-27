package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.BajaActivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BajaActivoRepository extends JpaRepository<BajaActivo, BajaActivo.Key> {
}
