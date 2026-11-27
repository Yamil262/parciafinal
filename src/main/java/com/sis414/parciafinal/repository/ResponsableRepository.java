package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, Responsable.Key> {
    List<Responsable> findByEntidadAndUnidadAndCodOfic(String entidad, String unidad, Integer codOfic);
}
