package com.gabrielravanhan.controller.dto;

import com.gabrielravanhan.domain.model.Clube;

public record ClubeDto(
        Long id,
        String nome
) {

    public ClubeDto(Clube clube) {
        this(clube.getId(), clube.getNome());
    }

    public Clube converterParaModel() {
        return new Clube(this.id, this.nome);
    }
}
