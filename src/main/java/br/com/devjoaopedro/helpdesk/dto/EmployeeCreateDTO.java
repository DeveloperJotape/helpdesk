package br.com.devjoaopedro.helpdesk.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.devjoaopedro.helpdesk.entity.enums.Department;
import br.com.devjoaopedro.helpdesk.entity.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto para criação de funcionário")
public record EmployeeCreateDTO(
        String name,
        String cpf,
        String phone,
        String email,
        String password,

        @Schema(description = "Departamento do funcionário. Deve ser um dos valores exatos: 'TECHNOLOGY', 'ACCOUNTING', 'LEGAL', 'COMMERCIAL', 'FINANCIAL'", example = "LEGAL") Department department,

        @Schema(description = "Cargo do funcionário (UserRole)", example = "DEVELOPER") UserRole userRole,
        Boolean isActive,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") @Schema(description = "Data de admissão no formato dd/MM/yyyy", example = "14/02/2025") LocalDate admissionDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") @Schema(description = "Data de desligamento no formato dd/MM/yyyy", example = "14/02/2026") @JsonInclude(JsonInclude.Include.NON_NULL) LocalDate departureDate) {

}
