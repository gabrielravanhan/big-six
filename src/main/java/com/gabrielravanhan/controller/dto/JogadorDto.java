package com.gabrielravanhan.controller.dto;

import com.gabrielravanhan.domain.model.Jogador;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public record JogadorDto(
        Long id,
        String nome,
        LocalDate dataNascimento,
        List<PosicaoDto> posicoes
) {

    public JogadorDto(Jogador jogador) {
        this(
                jogador.getId(),
                jogador.getNome(),
                jogador.getDataNascimento(),
                ofNullable(jogador.getPosicoes()).orElse(emptyList()).stream().map(PosicaoDto::new).collect(toList())
        );
    }

    public Jogador converterParaModel() {
        return new Jogador(
                this.id,
                this.nome,
                this.dataNascimento,
                ofNullable(this.posicoes).orElse(emptyList()).stream().map(PosicaoDto::converterParaModel).collect(toList())
        );
    }
}
