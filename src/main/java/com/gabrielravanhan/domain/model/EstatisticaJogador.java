package com.gabrielravanhan.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "estatisticas")
public class EstatisticaJogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Temporada temporada;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Jogador jogador;

    @PositiveOrZero
    @Column(nullable = false)
    private Long numeroJogos;

    @PositiveOrZero
    @Column(nullable = false)
    private Long numeroGols;

    @PositiveOrZero
    @Column(nullable = false)
    private Long numeroAssistencias;
}
