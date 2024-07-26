package com.gabrielravanhan.service.impl;

import com.gabrielravanhan.domain.repository.ClubeRepository;
import com.gabrielravanhan.service.ClubeService;
import com.gabrielravanhan.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubeServiceImpl implements ClubeService {

    @Autowired
    ClubeRepository clubeRepository;

    @Override
    public void buscarPeloId(Long id) {
        this.clubeRepository.findById(id).orElseThrow(() -> new NotFoundException("clube"));
    }
}
