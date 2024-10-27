package org.jrevolorio.api.proyectoIntegrador.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "reservations")
public class Reservation {

    @Id
    private int idReservation;
    private Long DPI;
    private LocalDate reservationDate;
    private String description;


}
