package com.gabrielravanhan.controller.dto;

import com.gabrielravanhan.domain.model.TemporadaJogador;

import static java.util.Optional.ofNullable;

public record TemporadaJogadorDto(
        Long id,
        TemporadaDto temporada,
        JogadorDto jogador,
        ClubeDto clube,
        Long numeroJogos,
        Long numeroGols,
        Long numeroAssistencias
) {

    public TemporadaJogadorDto(TemporadaJogador temporadaJogador) {
        this(
                temporadaJogador.getId(),
                ofNullable(temporadaJogador.getTemporada()).map(TemporadaDto::new).orElse(null),
                ofNullable(temporadaJogador.getJogador()).map(JogadorDto::new).orElse(null),
                ofNullable(temporadaJogador.getClube()).map(ClubeDto::new).orElse(null),
                temporadaJogador.getNumeroJogos(),
                temporadaJogador.getId(),
                temporadaJogador.getNumeroAssistencias()
        );
    }

    public TemporadaJogador converterParaModel() {
        return new TemporadaJogador(
                this.id,
                ofNullable(this.temporada).map(TemporadaDto::converterParaModel).orElse(null),
                ofNullable(this.jogador).map(JogadorDto::converterParaModel).orElse(null),
                ofNullable(this.clube).map(ClubeDto::converterParaModel).orElse(null),
                this.numeroJogos,
                this.numeroGols,
                this.numeroAssistencias
        );
    }
}
