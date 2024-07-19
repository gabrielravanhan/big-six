package com.gabrielravanhan.controller.dto;

import com.gabrielravanhan.domain.model.Temporada;

public record TemporadaDto(
        Long id,
        String periodo
) {

    public TemporadaDto(Temporada temporada) {
        this(temporada.getId(), temporada.getPeriodo());
    }

    public Temporada converterParaModel() {
        return new Temporada(this.id, this.periodo);
    }
}
