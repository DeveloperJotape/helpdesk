package br.com.devjoaopedro.helpdesk.dto;

import br.com.devjoaopedro.helpdesk.entity.enums.Priority;

import java.util.UUID;

public record TicketCreateDTO(
        String title,
        UUID requester,
        UUID requested,
        Priority priority) {

}
