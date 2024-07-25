package com.gabrielravanhan.domain.repository;

import com.gabrielravanhan.domain.model.Posicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosicaoRepository extends JpaRepository<Posicao, Long> {
}
