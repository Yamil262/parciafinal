package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.ObjetoGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetoGastoRepository extends JpaRepository<ObjetoGasto, ObjetoGasto.Key> {
}
