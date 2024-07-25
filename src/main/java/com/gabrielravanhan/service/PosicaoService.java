package com.gabrielravanhan.service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PosicaoService {

    void buscarPeloId(Long id);
}
