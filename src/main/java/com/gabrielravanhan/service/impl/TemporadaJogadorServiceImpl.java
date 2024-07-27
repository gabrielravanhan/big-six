package com.gabrielravanhan.service.impl;

import com.gabrielravanhan.domain.model.TemporadaJogador;
import com.gabrielravanhan.domain.repository.TemporadaJogadorRepository;
import com.gabrielravanhan.service.JogadorService;
import com.gabrielravanhan.service.TemporadaJogadorService;
import com.gabrielravanhan.service.TemporadaService;
import com.gabrielravanhan.service.exception.BusinessException;
import com.gabrielravanhan.service.exception.InvalidFieldException;
import com.gabrielravanhan.service.exception.NotFoundException;
import com.gabrielravanhan.service.exception.RequiredFieldException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Transactional
@Service
public class TemporadaJogadorServiceImpl implements TemporadaJogadorService {

    @Autowired
    TemporadaJogadorRepository temporadaJogadorRepository;

    @Autowired
    TemporadaService temporadaService;

    @Autowired
    JogadorService jogadorService;

    @Override
    public List<TemporadaJogador> buscarTodos() {
        return this.temporadaJogadorRepository.findAll();
    }

    @Override
    public TemporadaJogador buscarPeloId(Long id) {
        return this.temporadaJogadorRepository.findById(id).orElseThrow(() -> new NotFoundException("temporada do jogador"));
    }

    @Override
    public TemporadaJogador criar(TemporadaJogador temporadaJogador) {
        ofNullable(temporadaJogador).orElseThrow(() -> new BusinessException("As informações da temporada do jogador não podem ser nulas."));
        validarCamposTemporadaJogador(temporadaJogador);
        return this.temporadaJogadorRepository.save(temporadaJogador);
    }

    @Override
    public TemporadaJogador atualizar(Long id, TemporadaJogador temporadaJogador) {
        TemporadaJogador dbTemporadaJogador = this.buscarPeloId(id);
        if (!dbTemporadaJogador.getId().equals(temporadaJogador.getId())) {
            throw new BusinessException("Os indentificadores devem ser iguais.");
        }
        validarCamposTemporadaJogador(temporadaJogador);
        BeanUtils.copyProperties(temporadaJogador, dbTemporadaJogador, "id");
        return this.temporadaJogadorRepository.save(temporadaJogador);
    }

    @Override
    public void deletar(Long id) {
        TemporadaJogador temporadaJogador = this.buscarPeloId(id);
        this.temporadaJogadorRepository.delete(temporadaJogador);
    }

    private void validarCamposTemporadaJogador(TemporadaJogador temporadaJogador) {
        ofNullable(temporadaJogador.getTemporada()).orElseThrow(() -> new RequiredFieldException("temporada"));
        temporadaService.buscarPeloId(temporadaJogador.getTemporada().getId());

        ofNullable(temporadaJogador.getJogador()).orElseThrow(() -> new RequiredFieldException("jogador"));
        jogadorService.buscarPeloId(temporadaJogador.getJogador().getId());

        ofNullable(temporadaJogador.getNumeroJogos()).orElseThrow(() -> new RequiredFieldException("número de jogos"));
        if (temporadaJogador.getNumeroJogos() < 0)
            throw new InvalidFieldException("número de jogos");

        ofNullable(temporadaJogador.getNumeroGols()).orElseThrow(() -> new RequiredFieldException("número de gols"));
        if (temporadaJogador.getNumeroGols() < 0)
            throw new InvalidFieldException("número de gols");

        ofNullable(temporadaJogador.getNumeroAssistencias()).orElseThrow(() -> new RequiredFieldException("número de assistências"));
        if (temporadaJogador.getNumeroAssistencias() < 0)
            throw new InvalidFieldException("número de assistências");
    }
}
