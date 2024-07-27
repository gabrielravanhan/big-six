package com.gabrielravanhan.service.impl;

import com.gabrielravanhan.domain.model.Jogador;
import com.gabrielravanhan.domain.repository.JogadorRepository;
import com.gabrielravanhan.service.JogadorService;
import com.gabrielravanhan.service.PosicaoService;
import com.gabrielravanhan.service.exception.BusinessException;
import com.gabrielravanhan.service.exception.InvalidFieldException;
import com.gabrielravanhan.service.exception.NotFoundException;
import com.gabrielravanhan.service.exception.RequiredFieldException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static java.util.Optional.ofNullable;

@Transactional
@Service
public class JogadorServiceImpl implements JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private PosicaoService posicaoService;

    @Override
    public List<Jogador> buscarTodos() {
        return this.jogadorRepository.findAll();
    }

    @Override
    public Jogador buscarPeloId(Long id) {
        return this.jogadorRepository.findById(id).orElseThrow(() -> new NotFoundException("jogador"));
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
        ofNullable(jogador.getNome()).orElseThrow(() -> new RequiredFieldException("nome"));
        if (jogador.getNome().isEmpty())
            throw new InvalidFieldException("nome");

        ofNullable(jogador.getDataNascimento()).orElseThrow(() -> new RequiredFieldException("data de nascimento"));
        if (Period.between(jogador.getDataNascimento(), LocalDate.now()).getYears() < 18)
            throw new BusinessException("O jogador deve ser maior de 18 anos.", HttpStatus.UNPROCESSABLE_ENTITY);

        ofNullable(jogador.getClube()).orElseThrow(() -> new RequiredFieldException("clube"));

        if (jogador.getPosicoes().isEmpty())
            throw new RequiredFieldException("posições");
        jogador.getPosicoes().forEach(posicao -> posicaoService.buscarPeloId(posicao.getId()));
    }
}
