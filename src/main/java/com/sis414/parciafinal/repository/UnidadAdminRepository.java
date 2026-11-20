package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.UnidadAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadAdminRepository extends JpaRepository<UnidadAdmin, Long> {
}
