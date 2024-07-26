package com.gabrielravanhan.service.impl;

import com.gabrielravanhan.domain.repository.TemporadaRepository;
import com.gabrielravanhan.service.TemporadaService;
import com.gabrielravanhan.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemporadaServiceImpl implements TemporadaService {

    @Autowired
    TemporadaRepository temporadaRepository;

    @Override
    public void buscarPeloId(Long id) {
        this.temporadaRepository.findById(id).orElseThrow(() -> new NotFoundException("temporada"));
    }
}
