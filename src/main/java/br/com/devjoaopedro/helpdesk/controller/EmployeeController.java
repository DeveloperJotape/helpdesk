package br.com.devjoaopedro.helpdesk.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import br.com.devjoaopedro.helpdesk.dto.EmployeeCreateDTO;
import br.com.devjoaopedro.helpdesk.dto.EmployeeResponseDTO;
import br.com.devjoaopedro.helpdesk.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping
    @Operation(summary = "Cria um novo funcionário.")
    public ResponseEntity<EmployeeResponseDTO> create(@RequestBody EmployeeCreateDTO dto) {
        EmployeeResponseDTO response = service.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Lista todos os funcionários.")
    public ResponseEntity<List<EmployeeResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um funcionário pelo ID.")
    public ResponseEntity<EmployeeResponseDTO> getById(@PathVariable UUID id) {
        EmployeeResponseDTO response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @Operation(summary = "Busca funcionários pelo nome.")
    public ResponseEntity<List<EmployeeResponseDTO>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(service.getByName(name));
    }

    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Busca funcionário por CPF.")
    public ResponseEntity<EmployeeResponseDTO> getByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(service.getByCpf(cpf));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um funcionário.")
    public ResponseEntity<EmployeeResponseDTO> update(@PathVariable UUID id, @RequestBody EmployeeCreateDTO dto) {
        EmployeeResponseDTO response = service.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/activate")
    @Operation(summary = "Ativa um funcionário.")
    public ResponseEntity<EmployeeResponseDTO> activate(@PathVariable UUID id) {
        return ResponseEntity.ok(service.activate(id));

    }

    @PatchMapping("/{id}/deactivate")
    @Operation(summary = "Desativa um funcionário.")
    public ResponseEntity<EmployeeResponseDTO> deactivate(@PathVariable UUID id) {
        return ResponseEntity.ok(service.deactivate(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um funcionário.")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
