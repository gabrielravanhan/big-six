package com.gabrielravanhan.service.impl;

import com.gabrielravanhan.domain.repository.PosicaoRepository;
import com.gabrielravanhan.service.PosicaoService;
import com.gabrielravanhan.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PosicaoServiceImpl implements PosicaoService {

    @Autowired
    PosicaoRepository posicaoRepository;

    @Override
    public void buscarPeloId(Long id) {
        this.posicaoRepository.findById(id).orElseThrow(() -> new NotFoundException("posição"));
    }
}
