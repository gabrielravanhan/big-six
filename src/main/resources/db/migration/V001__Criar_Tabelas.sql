CREATE TABLE posicoes(
    id      BIGINT,
    nome    VARCHAR(255) UNIQUE NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE clubes(
    id      BIGINT,
    nome    VARCHAR(255) UNIQUE NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE temporadas(
    id      BIGINT,
    periodo VARCHAR(009) UNIQUE NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE jogadores(
    id              BIGINT,
    nome            VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    clube_id        BIGINT NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(clube_id) REFERENCES clubes(id)
);

CREATE TABLE estatisticas(
    id                  BIGINT,
    temporada_id        BIGINT NOT NULL,
    jogador_id          BIGINT NOT NULL,
    numero_jogos        BIGINT NOT NULL,
    numero_gols         BIGINT NOT NULL,
    numero_assistencias BIGINT NOT null,

    PRIMARY KEY(id),
    FOREIGN KEY(temporada_id) REFERENCES temporadas(id),
    FOREIGN KEY(jogador_id) REFERENCES jogadores(id)
);

CREATE TABLE posicoes_jogadores(
    id              BIGINT,
    posicao_id      BIGINT NOT NULL,
    jogador_id      BIGINT NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(posicao_id) REFERENCES posicoes(id),
    FOREIGN KEY(jogador_id) REFERENCES jogadores(id)
);
