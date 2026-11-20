package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.ActivoFijo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivoFijoRepository extends JpaRepository<ActivoFijo, Long> {
    Optional<ActivoFijo> findFirstByOrderByIdAsc();
    Optional<ActivoFijo> findFirstByOrderByIdDesc();
    Optional<ActivoFijo> findFirstByIdGreaterThanOrderByIdAsc(Long id);
    Optional<ActivoFijo> findFirstByIdLessThanOrderByIdDesc(Long id);
}
