package br.com.devjoaopedro.helpdesk.dto;

import javax.management.relation.Role;

import br.com.devjoaopedro.helpdesk.entity.enums.Department;

public record EmployeeCreateDTO(
        String name,
        String cpf,
        String phone,
        String email,
        String password,
        Department department,
        Role role,
        Boolean isActive) {

}
