package org.jrevolorio.api.proyectoIntegrador.controller.reservation;

import org.jrevolorio.api.proyectoIntegrador.model.Reservation;
import org.jrevolorio.api.proyectoIntegrador.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyectoIntegrador/v1/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/createReservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.createReservation(reservation));
    }

    @GetMapping("/getReservation/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable int idReservation) {
        return reservationService.getReservationById(idReservation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getReservations")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getReservations());
    }

    @PutMapping("/updateReservation/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable int idReservation, @RequestBody Reservation reservation) {
        Reservation updatedReservation = reservationService.updateReservation(idReservation, reservation);
        return updatedReservation != null ? ResponseEntity.ok(updatedReservation) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteReservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int idReservation) {
        reservationService.deleteReservation(idReservation);
        return ResponseEntity.noContent().build();
    }
}
