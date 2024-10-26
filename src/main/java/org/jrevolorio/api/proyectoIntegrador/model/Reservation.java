package org.jrevolorio.api.proyectoIntegrador.model;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {

    private Long id;
    private Long DPI;
    private LocalDate reservationDate;
    private String description;


}
