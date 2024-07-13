package com.gabrielravanhan.domain.repository;

import com.gabrielravanhan.domain.model.EstatisticaJogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstatisticaRepository extends JpaRepository<EstatisticaJogador, Long> {
}
