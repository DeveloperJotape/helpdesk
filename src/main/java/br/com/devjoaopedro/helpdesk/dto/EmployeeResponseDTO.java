package br.com.devjoaopedro.helpdesk.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.devjoaopedro.helpdesk.entity.enums.Department;
import br.com.devjoaopedro.helpdesk.entity.enums.UserRole;

public record EmployeeResponseDTO(
                UUID id,
                String name,
                String cpf,
                String phone,
                String email,
                Department department,
                UserRole userRole,
                Boolean isActive,

                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate admissionDate,

                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate departureDate) {

}
