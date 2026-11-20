package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.CuentaPartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaPartidaRepository extends JpaRepository<CuentaPartida, Long> {
}
