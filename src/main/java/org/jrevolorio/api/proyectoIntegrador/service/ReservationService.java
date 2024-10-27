package org.jrevolorio.api.proyectoIntegrador.service;

import org.jrevolorio.api.proyectoIntegrador.interfaces.ReservationRepository;
import org.jrevolorio.api.proyectoIntegrador.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {

        if (reservation.getIdReservation() == 0) {
            reservation.setIdReservation(generateReservationId());
        }

        return reservationRepository.save(reservation);
    }

    private Integer generateReservationId() {
        return (int) reservationRepository.count() + 1;
    }

    public Optional<Reservation> getReservationById(int idReservation) {
        return reservationRepository.findById(idReservation);
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Reservation updateReservation(int idReservation, Reservation reservation) {
        if (reservationRepository.existsById(idReservation)) {
            reservation.setIdReservation(idReservation);
            return reservationRepository.save(reservation);
        }
        return null;
    }

    public void deleteReservation(int idReservation) {
        reservationRepository.deleteById(idReservation);
    }
}
