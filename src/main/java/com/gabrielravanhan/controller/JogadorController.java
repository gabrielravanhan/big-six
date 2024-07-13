package com.gabrielravanhan.controller;

import com.gabrielravanhan.domain.model.Jogador;
import com.gabrielravanhan.service.JogadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/jogadores")
@RestController
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping
    @Operation(summary = "Buscar todos os jogadores", description = "Retorna uma lista com todos os jogadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso")
    })
    public ResponseEntity<List<Jogador>> bucarTodos() {
        var jogadores = this.jogadorService.buscarTodos();
        return ResponseEntity.ok(jogadores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um jogador pelo ID", description = "Retorna um jogador específico, de acordo com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado")
    })
    public ResponseEntity<Jogador> buscarPeloId(@PathVariable Long id) {
        var jogador = this.jogadorService.buscarPeloId(id);
        return ResponseEntity.ok(jogador);
    }

    @PostMapping
    @Operation(summary = "Criar um jogador", description = "Insere um jogador no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Jogador criado"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos")
    })
    public ResponseEntity<Jogador> criar(@RequestBody Jogador jogador) {
        var jog = jogadorService.criar(jogador);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(jogador.getId())
                .toUri();
        return ResponseEntity.created(location).body(jogador);
    }

    @PutMapping
    @Operation(summary = "Atualizar um jogador", description = "Atualiza um jogador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogador atualizado"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos")
    })
    public ResponseEntity<Jogador> atualizar(@RequestParam Long id, @RequestBody Jogador jogador) {
        var jog = jogadorService.atualizar(id, jogador);
        return ResponseEntity.ok(jog);
    }

    @DeleteMapping
    @Operation(summary = "Deletar um jogador", description = "Deleta um jogador do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Jogador deletado"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado")
    })
    public ResponseEntity<Void> deletar(@RequestParam Long id) {
        jogadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
