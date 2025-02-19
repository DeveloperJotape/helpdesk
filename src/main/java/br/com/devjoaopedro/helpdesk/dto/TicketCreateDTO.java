package br.com.devjoaopedro.helpdesk.dto;

import br.com.devjoaopedro.helpdesk.entity.enums.Priority;
import br.com.devjoaopedro.helpdesk.entity.enums.Status;

import java.util.UUID;

public record TicketCreateDTO(
        String title,
        String description,
        UUID requester,
        UUID requested,
        Priority priority,
        Status status) {

}
