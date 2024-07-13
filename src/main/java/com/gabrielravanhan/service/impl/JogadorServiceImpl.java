package com.gabrielravanhan.service.impl;

import com.gabrielravanhan.domain.model.Jogador;
import com.gabrielravanhan.domain.repository.JogadorRepository;
import com.gabrielravanhan.service.JogadorService;
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
public class JogadorServiceImpl implements JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Override
    public List<Jogador> buscarTodos() {
        return this.jogadorRepository.findAll();
    }

    @Override
    public Jogador buscarPeloId(Long id) {
        return this.jogadorRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Jogador criar(Jogador jogador) {
        ofNullable(jogador).orElseThrow(() -> new BusinessException("O jogador não pode ser nulo."));
        validarCamposJogador(jogador);
        return this.jogadorRepository.save(jogador);
    }

    @Override
    public Jogador atualizar(Long id, Jogador jogador) {
        Jogador dbJogador = this.buscarPeloId(id);
        if (!dbJogador.getId().equals(jogador.getId())) {
            throw new BusinessException("Os indentificadores devem ser iguais.");
        }
        validarCamposJogador(jogador);
        BeanUtils.copyProperties(jogador, dbJogador, "id");
        return this.jogadorRepository.save(jogador);
    }

    @Override
    public void deletar(Long id) {
        Jogador jogador = this.buscarPeloId(id);
        this.jogadorRepository.delete(jogador);
    }

    private void validarCamposJogador(Jogador jogador) {
        ofNullable(jogador.getNome()).orElseThrow(() -> new NullFieldException("o nome do jogador"));
        ofNullable(jogador.getDataNascimento()).orElseThrow(() -> new NullFieldException("a data de nascimento do jogador"));
        ofNullable(jogador.getClube()).orElseThrow(() -> new NullFieldException("o clube do jogador"));
        ofNullable(jogador.getPosicoes()).orElseThrow(() -> new NullFieldException("as posições do jogador"));
    }
}
