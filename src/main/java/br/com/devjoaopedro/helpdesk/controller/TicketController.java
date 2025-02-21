package br.com.devjoaopedro.helpdesk.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.devjoaopedro.helpdesk.dto.TicketCreateDTO;
import br.com.devjoaopedro.helpdesk.dto.TicketResponseDTO;
import br.com.devjoaopedro.helpdesk.entity.enums.Status;
import br.com.devjoaopedro.helpdesk.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Chamados", description = "API para gerenciamento de chamados")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    private TicketService service;

    @PostMapping
    @Operation(summary = "Cria um novo chamado", description = "Cria um novo chamado utilizando os IDs dos colaboradores (requisitante e requisitado)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chamado criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<TicketResponseDTO> create(@RequestBody TicketCreateDTO dto) {
        TicketResponseDTO response = service.create(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um chamado", description = "Atualiza os dados de um chamado existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chamado atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Chamado não encontrado")
    })
    public ResponseEntity<TicketResponseDTO> update(@PathVariable UUID id, @RequestBody TicketCreateDTO dto) {
        TicketResponseDTO response = service.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Lista todos os chamados", description = "Retorna uma lista com todos os chamados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de chamados retornada com sucesso")
    })
    public ResponseEntity<List<TicketResponseDTO>> getAll() {
        List<TicketResponseDTO> responses = service.getAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um chamado por ID", description = "Retorna o chamado com base no ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chamado encontrado"),
            @ApiResponse(responseCode = "404", description = "Chamado não encontrado")
    })
    public ResponseEntity<TicketResponseDTO> getById(@PathVariable UUID id) {
        TicketResponseDTO response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/title/{title}")
    @Operation(summary = "Busca chamados por título", description = "Retorna uma lista com todos os chamados cujo título contém a palavra-chave informada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de chamados retornada com sucesso")
    })
    public ResponseEntity<List<TicketResponseDTO>> getByTitle(@PathVariable String title) {
        return ResponseEntity.ok(service.getByTitle(title));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Atualiza o status do chamado", description = "Atualiza o status do chamado para OPEN, CLOSED ou ON_GOING")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Chamado não encontrado")
    })
    public ResponseEntity<TicketResponseDTO> updateStatus(@PathVariable UUID id, @RequestParam Status status) {
        TicketResponseDTO response = service.updateStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um chamado", description = "Remove um chamado do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Chamado excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Chamado não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
