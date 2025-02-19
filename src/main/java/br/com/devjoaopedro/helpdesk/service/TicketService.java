package br.com.devjoaopedro.helpdesk.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devjoaopedro.helpdesk.dto.TicketCreateDTO;
import br.com.devjoaopedro.helpdesk.dto.TicketResponseDTO;
import br.com.devjoaopedro.helpdesk.entity.Employee;
import br.com.devjoaopedro.helpdesk.entity.Ticket;
import br.com.devjoaopedro.helpdesk.entity.enums.Status;
import br.com.devjoaopedro.helpdesk.repository.EmployeeRepository;
import br.com.devjoaopedro.helpdesk.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TicketService {

        @Autowired
        private TicketRepository ticketRepository;

        @Autowired
        private EmployeeRepository employeeRepository;

        // Cria um chamado
        public TicketResponseDTO create(TicketCreateDTO dto) {
                // Busca os colaboradores
                Employee requester = employeeRepository.findById(dto.requester())
                                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado."));
                Employee requested = employeeRepository.findById(dto.requested())
                                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado."));

                Ticket ticket = new Ticket();
                ticket.setTitle(dto.title());
                ticket.setDescription(dto.description());
                ticket.setRequester(requester);
                ticket.setRequested(requested);
                ticket.setPriority(dto.priority());
                ticket.setStatus(dto.status());

                Ticket savedTicket = ticketRepository.save(ticket);
                return new TicketResponseDTO(
                                savedTicket.getId(),
                                savedTicket.getTitle(),
                                savedTicket.getDescription(),
                                savedTicket.getStartDate(),
                                savedTicket.getEndDate(),
                                savedTicket.getRequester().getName(),
                                savedTicket.getRequested().getName(),
                                savedTicket.getPriority(),
                                savedTicket.getStatus());
        }

        // Atualiza um chamado
        public TicketResponseDTO update(UUID id, TicketCreateDTO dto) {
                Ticket ticket = ticketRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado."));

                // Atualiza os dados do chamado
                ticket.setTitle(dto.title());
                ticket.setDescription(dto.description());

                // Atualiza os colaboradores
                Employee requester = employeeRepository.findById(dto.requester())
                                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado."));
                Employee requested = employeeRepository.findById(dto.requested())
                                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado."));
                ticket.setRequester(requester);
                ticket.setRequested(requested);

                ticket.setPriority(dto.priority());
                ticket.setStatus(dto.status());

                ticketRepository.save(ticket);
                return mapToResponseDTO(ticket);
        }

        // Lista todos chamados
        public List<TicketResponseDTO> getAll() {
                return ticketRepository.findAll().stream()
                                .map(this::mapToResponseDTO)
                                .collect(Collectors.toList());
        }

        // Busca um chamado pelo ID
        public TicketResponseDTO getById(UUID id) {
                Ticket ticket = ticketRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado."));
                return mapToResponseDTO(ticket);
        }

        // Busca chamados por título
        public List<TicketResponseDTO> getByTitle(String title) {
                return ticketRepository.findByTitleContainingIgnoreCase(title)
                                .stream()
                                .map(this::mapToResponseDTO)
                                .collect(Collectors.toList());
        }

        // Atualiza status do chamado
        public TicketResponseDTO updateStatus(UUID id, Status newStatus) {
                Ticket ticket = ticketRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Chamado não encontrado."));
                ticket.setStatus(newStatus);
                if (newStatus == Status.CLOSED) {
                        ticket.setEndDate(LocalDateTime.now());
                } else {
                        ticket.setEndDate(null); // Caso seja reaberto ou alterado para outro status
                }
                ticketRepository.save(ticket);
                return mapToResponseDTO(ticket);
        }

        // Exclui chamado
        public void delete(UUID id) {
                Ticket ticket = ticketRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado."));
                ticketRepository.delete(ticket);
        }

        private TicketResponseDTO mapToResponseDTO(Ticket ticket) {
                return new TicketResponseDTO(
                                ticket.getId(),
                                ticket.getTitle(),
                                ticket.getDescription(),
                                ticket.getStartDate(),
                                ticket.getEndDate(),
                                ticket.getRequester().getName(),
                                ticket.getRequested().getName(),
                                ticket.getPriority(),
                                ticket.getStatus());
        }
}
