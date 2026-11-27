package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Config.Key> {
}
