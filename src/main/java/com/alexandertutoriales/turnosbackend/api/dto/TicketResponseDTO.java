package com.alexandertutoriales.turnosbackend.api.dto;

import java.time.Instant;

import com.alexandertutoriales.turnosbackend.ticket.TicketStatus;

public record TicketResponseDTO(Long id, String code, TicketStatus status, Instant createdAt, Instant calledAt, Instant serveAt,
                                Integer moduleNumber, String fullName) {

}
