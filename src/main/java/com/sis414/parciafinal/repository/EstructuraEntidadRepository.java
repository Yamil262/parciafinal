package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.EstructuraEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstructuraEntidadRepository extends JpaRepository<EstructuraEntidad, EstructuraEntidad.Key> {
}
