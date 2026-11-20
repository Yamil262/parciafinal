package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.Revalorizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevalorizacionRepository extends JpaRepository<Revalorizacion, Long> {
}
