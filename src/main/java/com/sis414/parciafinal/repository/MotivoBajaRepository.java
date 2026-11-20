package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.MotivoBaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoBajaRepository extends JpaRepository<MotivoBaja, Long> {
}
