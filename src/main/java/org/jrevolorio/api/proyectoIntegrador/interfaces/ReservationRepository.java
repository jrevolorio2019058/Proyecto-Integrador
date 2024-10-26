package org.jrevolorio.api.proyectoIntegrador.interfaces;

import org.jrevolorio.api.proyectoIntegrador.model.Reservation;

import java.util.List;

public interface ReservationRepository {

    Reservation createReservation(Reservation reservation);
    Reservation getReservationById(Long id);
    List<Reservation> getReservations();
    Reservation updateReservation(Long id, Reservation reservation);
    void deleteReservation(Long id);

}
