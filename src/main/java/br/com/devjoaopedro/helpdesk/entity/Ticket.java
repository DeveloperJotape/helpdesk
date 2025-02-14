package br.com.devjoaopedro.helpdesk.entity;

import br.com.devjoaopedro.helpdesk.entity.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.tomcat.util.http.parser.Priority;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_ticket")
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Data/hora de abertura do chamado.
     * - @CreationTimestamp insere automaticamente a data/hora atual quando o
     * registro é criado.
     * - @JsonFormat define o formato de exibição no JSON.
     * - updatable = false impede que seja alterado após persistido.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "start_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime startDate;

    /**
     * Data/hora de fechamento ou finalização do chamado.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "end_date")
    private LocalDateTime endDate;

    /**
     * Quem abriu o chamado.
     */
    @ManyToOne
    @JoinColumn(name = "requestor_id")
    private Employee requester;

    /**
     * Funcionário responsável / destinatário do chamado.
     */
    @ManyToOne
    @JoinColumn(name = "requested_id")
    private Employee requested;

    /**
     * Prioridade do chamado.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    /**
     * Status do chamado.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.OPEN;

    @PrePersist
    public void prePersist() {
        this.startDate = LocalDateTime.now(); // Define a data de criação automaticamente
    }

}
