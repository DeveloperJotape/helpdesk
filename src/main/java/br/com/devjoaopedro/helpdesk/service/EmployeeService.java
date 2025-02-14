package br.com.devjoaopedro.helpdesk.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devjoaopedro.helpdesk.dto.EmployeeCreateDTO;
import br.com.devjoaopedro.helpdesk.dto.EmployeeResponseDTO;
import br.com.devjoaopedro.helpdesk.entity.Employee;
import br.com.devjoaopedro.helpdesk.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    // Cria funcionário
    // TODO: Tirar duvidas sobre data de admissao e saída
    public EmployeeResponseDTO create(EmployeeCreateDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.name());
        employee.setCpf(dto.cpf());
        employee.setPhone(dto.phone());
        employee.setEmail(dto.email());
        employee.setPassword(dto.password());
        employee.setDepartment(dto.department());
        employee.setUserRole(dto.userRole());
        employee.setIsActive(dto.isActive());
        employee.setAdmission(dto.admissionDate());
        employee.setDeparture(dto.departureDate());

        repository.save(employee);
        return mapToResponseDTO(employee);
    }

    // Atualização de funcionário
    public EmployeeResponseDTO update(UUID id, EmployeeCreateDTO dto) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));
        employee.setName(dto.name());
        employee.setCpf(dto.cpf());
        employee.setPhone(dto.phone());
        employee.setEmail(dto.email());
        // Atualiza a senha se necessário ou deixe sem alteração
        employee.setDepartment(dto.department());
        employee.setUserRole(dto.userRole());
        employee.setIsActive(dto.isActive());
        employee.setAdmission(dto.admissionDate());
        employee.setDeparture(dto.departureDate());

        employee = repository.save(employee);
        return mapToResponseDTO(employee);
    }

    // Lista todos funcionários
    public List<EmployeeResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Lista por ID
    public EmployeeResponseDTO getById(UUID id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));
        return mapToResponseDTO(employee);
    }

    // Buscar por nome
    public List<EmployeeResponseDTO> getByName(String name) {
        return repository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Ativar funcionário
    public EmployeeResponseDTO activate(UUID id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));
        employee.setIsActive(true);
        employee = repository.save(employee);
        return mapToResponseDTO(employee);
    }

    // Desativar funcionário
    public EmployeeResponseDTO deactivate(UUID id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));
        employee.setIsActive(false);
        employee = repository.save(employee);
        return mapToResponseDTO(employee);
    }

    // Deletar funcionário (restrição de ADMIN será aplicada futuramente)
    public void delete(UUID id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));
        repository.delete(employee);
    }

    /*
     * 
     * Método para mapear Employee para EmployeeResponseDTO
     * 
     */
    private EmployeeResponseDTO mapToResponseDTO(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getCpf(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getUserRole(),
                employee.getIsActive(),
                employee.getAdmission(),
                employee.getDeparture());
    }

}
