package br.com.devjoaopedro.helpdesk.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.devjoaopedro.helpdesk.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    // Busca funcionários por nome (ignora letras maiúsculas e minúsculas)
    List<Employee> findByNameContainingIgnoreCase(String name);

    // Busca funcionário por CPF
    @Query("SELECT e FROM Employee e WHERE e.cpf = :cpf")
    Employee findByCpf(@Param("cpf") String cpf);

}
