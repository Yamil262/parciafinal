package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.OrganismoFin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganismoFinRepository extends JpaRepository<OrganismoFin, Long> {
}
