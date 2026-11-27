package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.OrganismoFin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganismoFinRepository extends JpaRepository<OrganismoFin, OrganismoFin.Key> {
    Optional<OrganismoFin> findFirstByCodOfOrderByGestionDesc(String codOf);
}
