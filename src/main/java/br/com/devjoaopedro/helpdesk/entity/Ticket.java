package br.com.devjoaopedro.helpdesk.entity;

import java.io.ObjectInputFilter.Status;
import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.tomcat.util.http.parser.Priority;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_ticket")
@Data
public class Ticket {

    private UUID id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Priority priority;
    private Status status;
    private Employee employee;

}
