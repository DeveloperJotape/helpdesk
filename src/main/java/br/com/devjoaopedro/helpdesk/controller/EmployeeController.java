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
import org.springframework.web.bind.annotation.RestController;

import br.com.devjoaopedro.helpdesk.dto.EmployeeCreateDTO;
import br.com.devjoaopedro.helpdesk.dto.EmployeeResponseDTO;
import br.com.devjoaopedro.helpdesk.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Colaboradores", description = "API para gerenciamento de chamados")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping
    @Operation(summary = "Cria um novo colaborador", description = "Registra um novo colaborador no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados")
    })
    public ResponseEntity<EmployeeResponseDTO> create(@RequestBody EmployeeCreateDTO dto) {
        EmployeeResponseDTO response = service.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Lista todos os colaboradores", description = "Lista todos os colaboradores, ativos e inativos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos os colaboradores")
    })
    public ResponseEntity<List<EmployeeResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um colaborador por ID", description = "Retorna um colaborador com base no ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador encontrado"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    public ResponseEntity<EmployeeResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Busca colaboradores pelo nome", description = "Retorna uma lista de colaboradores que possuem o nome informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador(es) encontrado(s)"),
            @ApiResponse(responseCode = "404", description = "Nenhum colaborador encontrado com esse nome")
    })
    public ResponseEntity<List<EmployeeResponseDTO>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getByName(name));
    }

    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Busca um colaborador por CPF", description = "Retorna um colaborador com base no CPF informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador encontrado"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    public ResponseEntity<EmployeeResponseDTO> getByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(service.getByCpf(cpf));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um colaborador", description = "Atualiza os dados de um colaborador existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    public ResponseEntity<EmployeeResponseDTO> update(@PathVariable UUID id, @RequestBody EmployeeCreateDTO dto) {
        EmployeeResponseDTO response = service.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/activate")
    @Operation(summary = "Ativa um colaborador", description = "Ativa um colaborador que está inativo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador ativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    public ResponseEntity<EmployeeResponseDTO> activate(@PathVariable UUID id) {
        return ResponseEntity.ok(service.activate(id));

    }

    @PatchMapping("/{id}/deactivate")
    @Operation(summary = "Desativa um colaborador", description = "Desativa um colaborador que está ativo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador desativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    public ResponseEntity<EmployeeResponseDTO> deactivate(@PathVariable UUID id) {
        return ResponseEntity.ok(service.deactivate(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um colaborador", description = "Exclui um colaborador permanentemente do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Colaborador excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
