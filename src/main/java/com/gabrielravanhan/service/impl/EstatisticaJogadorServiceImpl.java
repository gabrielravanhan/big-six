package com.gabrielravanhan.service.impl;

import com.gabrielravanhan.domain.model.EstatisticaJogador;
import com.gabrielravanhan.domain.model.Jogador;
import com.gabrielravanhan.domain.repository.EstatisticaRepository;
import com.gabrielravanhan.service.EstatisticaJogadorService;
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
public class EstatisticaJogadorServiceImpl implements EstatisticaJogadorService {

    @Autowired
    EstatisticaRepository estatisticaRepository;

    @Override
    public List<EstatisticaJogador> buscarTodos() {
        return this.estatisticaRepository.findAll();
    }

    @Override
    public EstatisticaJogador buscarPeloId(Long id) {
        return this.estatisticaRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public EstatisticaJogador criar(EstatisticaJogador estatisticaJogador) {
        ofNullable(estatisticaJogador).orElseThrow(() -> new BusinessException("As informações da temporada do jogador não pode ser nulo."));
        validarCamposTemporadaJogador(estatisticaJogador);
        return this.estatisticaRepository.save(estatisticaJogador);
    }

    @Override
    public EstatisticaJogador atualizar(Long id, EstatisticaJogador estatisticaJogador) {
        EstatisticaJogador dbEstatisticaJogador = this.buscarPeloId(id);
        if (!dbEstatisticaJogador.getId().equals(estatisticaJogador.getId())) {
            throw new BusinessException("Os indentificadores devem ser iguais.");
        }
        validarCamposTemporadaJogador(estatisticaJogador);
        BeanUtils.copyProperties(estatisticaJogador, dbEstatisticaJogador, "id");
        return this.estatisticaRepository.save(estatisticaJogador);
    }

    @Override
    public void deletar(Long id) {
        EstatisticaJogador estatisticaJogador = this.buscarPeloId(id);
        this.estatisticaRepository.delete(estatisticaJogador);
    }

    private void validarCamposTemporadaJogador(EstatisticaJogador estatisticaJogador) {
        ofNullable(estatisticaJogador.getTemporada()).orElseThrow(() -> new NullFieldException("a temporada"));
        ofNullable(estatisticaJogador.getJogador()).orElseThrow(() -> new NullFieldException("o jogador"));
        ofNullable(estatisticaJogador.getNumeroJogos()).orElseThrow(() -> new NullFieldException("número de jogos"));
        ofNullable(estatisticaJogador.getNumeroGols()).orElseThrow(() -> new NullFieldException("número de jogos"));
        ofNullable(estatisticaJogador.getNumeroAssistencias()).orElseThrow(() -> new NullFieldException("número de assistências"));
    }
}
