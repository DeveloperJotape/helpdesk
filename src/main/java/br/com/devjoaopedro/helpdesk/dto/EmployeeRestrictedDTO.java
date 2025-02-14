package br.com.devjoaopedro.helpdesk.dto;

import br.com.devjoaopedro.helpdesk.entity.enums.Department;

public record EmployeeRestrictedDTO(
        String name,
        Department department) {

}
