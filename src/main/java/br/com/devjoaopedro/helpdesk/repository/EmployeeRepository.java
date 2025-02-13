package br.com.devjoaopedro.helpdesk.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devjoaopedro.helpdesk.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

}
