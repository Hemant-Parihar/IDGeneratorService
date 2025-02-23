package com.idgeneratorservice.repository;

import com.idgeneratorservice.entity.GeneratedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GeneratedIdRepository extends JpaRepository<GeneratedId, Long> {

}
