package br.com.devjoaopedro.helpdesk.entity;

import java.time.LocalDate;
import java.util.UUID;

import javax.management.relation.Role;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.devjoaopedro.helpdesk.entity.enums.Department;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String cpf;
    private String phone;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_active")
    private Boolean isActive;

    /* Exemple: 24-11-2024 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "admission_date")
    private LocalDate admission;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "departure_date")
    private LocalDate departure;

}
