package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Oficina.Key> {
}
