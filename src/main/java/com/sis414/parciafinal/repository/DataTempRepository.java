package com.sis414.parciafinal.repository;

import com.sis414.parciafinal.entity.DataTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataTempRepository extends JpaRepository<DataTemp, Long> {
}
