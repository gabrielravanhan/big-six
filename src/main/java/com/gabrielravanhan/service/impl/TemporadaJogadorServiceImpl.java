package com.gabrielravanhan.service.impl;

import com.gabrielravanhan.domain.model.TemporadaJogador;
import com.gabrielravanhan.domain.repository.EstatisticaRepository;
import com.gabrielravanhan.service.TemporadaJogadorService;
import com.gabrielravanhan.service.exception.BusinessException;
import com.gabrielravanhan.service.exception.NotFoundException;
import com.gabrielravanhan.service.exception.NullFieldException;
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
    EstatisticaRepository estatisticaRepository;

    @Override
    public List<TemporadaJogador> buscarTodos() {
        return this.estatisticaRepository.findAll();
    }

    @Override
    public TemporadaJogador buscarPeloId(Long id) {
        return this.estatisticaRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public TemporadaJogador criar(TemporadaJogador temporadaJogador) {
        ofNullable(temporadaJogador).orElseThrow(() -> new BusinessException("As informações da temporada do jogador não podem ser nulas."));
        validarCamposTemporadaJogador(temporadaJogador);
        return this.estatisticaRepository.save(temporadaJogador);
    }

    @Override
    public TemporadaJogador atualizar(Long id, TemporadaJogador temporadaJogador) {
        TemporadaJogador dbTemporadaJogador = this.buscarPeloId(id);
        if (!dbTemporadaJogador.getId().equals(temporadaJogador.getId())) {
            throw new BusinessException("Os indentificadores devem ser iguais.");
        }
        validarCamposTemporadaJogador(temporadaJogador);
        BeanUtils.copyProperties(temporadaJogador, dbTemporadaJogador, "id");
        return this.estatisticaRepository.save(temporadaJogador);
    }

    @Override
    public void deletar(Long id) {
        TemporadaJogador temporadaJogador = this.buscarPeloId(id);
        this.estatisticaRepository.delete(temporadaJogador);
    }

    private void validarCamposTemporadaJogador(TemporadaJogador temporadaJogador) {
        ofNullable(temporadaJogador.getTemporada()).orElseThrow(() -> new NullFieldException("a temporada"));
        ofNullable(temporadaJogador.getJogador()).orElseThrow(() -> new NullFieldException("o jogador"));
        ofNullable(temporadaJogador.getNumeroJogos()).orElseThrow(() -> new NullFieldException("o número de jogos"));
        ofNullable(temporadaJogador.getNumeroGols()).orElseThrow(() -> new NullFieldException("o número de jogos"));
        ofNullable(temporadaJogador.getNumeroAssistencias()).orElseThrow(() -> new NullFieldException("o número de assistências"));
    }
}
