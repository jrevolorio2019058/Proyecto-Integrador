package org.jrevolorio.api.proyectoIntegrador.service;

import org.jrevolorio.api.proyectoIntegrador.interfaces.ReservationRepository;
import org.jrevolorio.api.proyectoIntegrador.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReservationService implements ReservationRepository{

    private final HashMap<Long, Reservation> reservationStorage = new HashMap<>();

    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public Reservation createReservation(Reservation reservation) {

        Long id = idCounter.incrementAndGet();

        reservation.setId(id);

        reservationStorage.put(id, reservation);

        return reservation;

    }

    @Override
    public Reservation getReservationById(Long id) {

        return reservationStorage.get(id);

    }

    @Override
    public List<Reservation> getReservations() {

        return new ArrayList<>(reservationStorage.values());

    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {

        if (reservationStorage.containsKey(id)) {
            Reservation existingReservation = reservationStorage.get(id);
            existingReservation.setReservationDate(reservation.getReservationDate());
            existingReservation.setId(reservation.getId());
            return existingReservation;
        }

        return null;

    }

    @Override
    public void deleteReservation(Long id) {

        reservationStorage.remove(id);

    }

}
