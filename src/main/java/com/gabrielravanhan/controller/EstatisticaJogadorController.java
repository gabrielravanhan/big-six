package com.gabrielravanhan.controller;

import com.gabrielravanhan.domain.model.EstatisticaJogador;
import com.gabrielravanhan.service.EstatisticaJogadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/temporadas-jogadores")
@RestController
public class EstatisticaJogadorController {

    @Autowired
    private EstatisticaJogadorService estatisticaJogadorService;

    @GetMapping
    @Operation(summary = "Buscar todas as temporadas dos jogadores", description = "Retorna uma lista com todas as temporadas dos jogadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso")
    })
    public ResponseEntity<List<EstatisticaJogador>> bucarTodos() {
        var estatisticasJogadores = this.estatisticaJogadorService.buscarTodos();
        return ResponseEntity.ok(estatisticasJogadores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar temporada de um jogador pelo ID", description = "Retorna uma temporada de um jogador específico, de acordo com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Temporada do jogador não encontrada")
    })
    public ResponseEntity<EstatisticaJogador> buscarPeloId(@PathVariable Long id) {
        var estatisticasJogadores = this.estatisticaJogadorService.buscarPeloId(id);
        return ResponseEntity.ok(estatisticasJogadores);
    }

    @PostMapping
    @Operation(summary = "Criar informações da temporada de um jogador", description = "Insere informações da temporada de um jogador no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Jogador criado"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos")
    })
    public ResponseEntity<EstatisticaJogador> criar(@RequestBody EstatisticaJogador estatisticaJogador) {
        var estJog = estatisticaJogadorService.criar(estatisticaJogador);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(estatisticaJogador.getId())
                .toUri();
        return ResponseEntity.created(location).body(estatisticaJogador);
    }

    @PutMapping
    @Operation(summary = "Atualizar um jogador", description = "Atualiza um jogador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogador atualizado"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos")
    })
    public ResponseEntity<EstatisticaJogador> atualizar(@RequestParam Long id, @RequestBody EstatisticaJogador estatisticaJogador) {
        var estJog = estatisticaJogadorService.atualizar(id, estatisticaJogador);
        return ResponseEntity.ok(estJog);
    }

    @DeleteMapping
    @Operation(summary = "Deletar um jogador", description = "Deleta um jogador do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Temporada do jogador deletado"),
            @ApiResponse(responseCode = "404", description = "Temporada do jogador não encontrada")
    })
    public ResponseEntity<Void> deletar(@RequestParam Long id) {
        estatisticaJogadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
