package br.com.devjoaopedro.helpdesk.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.devjoaopedro.helpdesk.entity.enums.Priority;
import br.com.devjoaopedro.helpdesk.entity.enums.Status;

public record TicketResponseDTO(
                UUID id,
                String title,
                String description,
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime startDate,
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime endDate,
                String requester,
                String requested,
                Priority priority,
                Status status) {

}
