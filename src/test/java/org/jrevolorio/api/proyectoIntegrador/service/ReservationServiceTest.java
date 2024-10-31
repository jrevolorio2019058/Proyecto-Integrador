package org.jrevolorio.api.proyectoIntegrador.service;

import org.jrevolorio.api.proyectoIntegrador.interfaces.ReservationRepository;
import org.jrevolorio.api.proyectoIntegrador.model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearReservacion
            () {
        Reservation reservation = new Reservation();

        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation createdReservation = reservationService.createReservation(reservation);

        assertNotNull(createdReservation);

    }

    @Test
    public void testVerReservacionEspecifica() {

        int id = 1;

        Reservation reservation = new Reservation();

        when(reservationRepository.findById(id)).thenReturn(Optional.of(reservation));

        Optional<Reservation> foundReservation = reservationService.getReservationById(id);

        assertTrue(foundReservation.isPresent());

    }

    @Test
    public void testEliminarReservacion() {

        int id = 1;

        doNothing().when(reservationRepository).deleteById(id);

        reservationService.deleteReservation(id);

        verify(reservationRepository, times(1)).deleteById(id);

    }

    @Test
    public void testObtenerReservaciones() {

        reservationService.getReservations();

        verify(reservationRepository, times(1)).findAll();

    }

}
