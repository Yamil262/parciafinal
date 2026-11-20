package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.Respaldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespaldoRepository extends JpaRepository<Respaldo, Long> {
}
