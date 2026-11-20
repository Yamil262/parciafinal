package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.OrgUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrgUserRepository extends JpaRepository<OrgUser, Long> {
    Optional<OrgUser> findByTypeAndIdUser(String type, String idUser);
}
