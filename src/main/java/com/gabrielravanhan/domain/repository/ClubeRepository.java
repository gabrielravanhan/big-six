package com.gabrielravanhan.domain.repository;

import com.gabrielravanhan.domain.model.Clube;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubeRepository extends JpaRepository<Clube, Long> {
}
