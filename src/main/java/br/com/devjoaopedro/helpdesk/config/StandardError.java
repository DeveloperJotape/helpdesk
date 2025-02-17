package br.com.devjoaopedro.helpdesk.config;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StandardError {
    private Integer status;
    private String message;
    private LocalDateTime timestamp;
}
