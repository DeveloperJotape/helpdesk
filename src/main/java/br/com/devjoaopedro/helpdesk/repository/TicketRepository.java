package br.com.devjoaopedro.helpdesk.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devjoaopedro.helpdesk.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    // Busca chamados por titulo (ignora letras maiúsculas e minúsculas)
    List<Ticket> findByTitleContainingIgnoreCase(String title);

}
