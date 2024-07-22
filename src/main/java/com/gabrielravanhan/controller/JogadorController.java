package com.gabrielravanhan.controller;

import com.gabrielravanhan.controller.dto.JogadorDto;
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
    @Operation(
            summary = "Buscar todos os jogadores",
            description = "Retorna uma lista com todos os jogadores"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso")
    })
    public ResponseEntity<List<JogadorDto>> bucarTodos() {
        List<JogadorDto> jogadoresDto = this.jogadorService.buscarTodos().stream().map(JogadorDto::new).toList();
        return ResponseEntity.ok(jogadoresDto);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar um jogador pelo ID",
            description = "Retorna um jogador específico, de acordo com o ID informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado")
    })
    public ResponseEntity<JogadorDto> buscarPeloId(@PathVariable Long id) {
        Jogador jogador = this.jogadorService.buscarPeloId(id);
        return ResponseEntity.ok(new JogadorDto(jogador));
    }

    @PostMapping
    @Operation(
            summary = "Criar um jogador",
            description = "Insere um jogador no banco de dados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Jogador criado"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos")
    })
    public ResponseEntity<JogadorDto> criar(@RequestBody JogadorDto jogadorDto) {
        Jogador jogador = jogadorService.criar(jogadorDto.converterParaModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(jogador.getId())
                .toUri();
        return ResponseEntity.created(location).body(new JogadorDto(jogador));
    }

    @PutMapping
    @Operation(
            summary = "Atualizar um jogador",
            description = "Atualiza um jogador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogador atualizado"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos")
    })
    public ResponseEntity<JogadorDto> atualizar(@RequestParam Long id, @RequestBody JogadorDto jogadorDto) {
        Jogador dbJogador = jogadorService.atualizar(id, jogadorDto.converterParaModel());
        return ResponseEntity.ok(new JogadorDto(dbJogador));
    }

    @DeleteMapping
    @Operation(
            summary = "Deletar um jogador",
            description = "Deleta um jogador do banco de dados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Jogador deletado"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado")
    })
    public ResponseEntity<Void> deletar(@RequestParam Long id) {
        jogadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
