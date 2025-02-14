package br.com.devjoaopedro.helpdesk.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devjoaopedro.helpdesk.entity.Ticket;

public interface TicketRepository extends JpaRepository<UUID, Ticket> {

}
