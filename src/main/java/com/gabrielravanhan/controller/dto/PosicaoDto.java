package com.gabrielravanhan.controller.dto;

import com.gabrielravanhan.domain.model.Posicao;

public record PosicaoDto(
        Long id,
        String nome
) {

    public PosicaoDto(Posicao posicao) {
        this(posicao.getId(), posicao.getNome());
    }

    public Posicao converterParaModel() {
        return new Posicao(this.id, this.nome);
    }
}
