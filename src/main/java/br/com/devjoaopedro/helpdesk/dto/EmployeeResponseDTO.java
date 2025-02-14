package br.com.devjoaopedro.helpdesk.dto;

import java.time.LocalDate;
import java.util.UUID;

import javax.management.relation.Role;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.devjoaopedro.helpdesk.entity.enums.Department;

public record EmployeeResponseDTO(
        UUID id,
        String name,
        String cpf,
        String phone,
        String email,
        Department department,
        Role role,
        Boolean isActive,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate admissionDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate departureDate) {

}
