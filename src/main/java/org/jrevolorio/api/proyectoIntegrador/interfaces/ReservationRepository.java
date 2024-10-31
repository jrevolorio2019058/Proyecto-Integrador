package org.jrevolorio.api.proyectoIntegrador.interfaces;

import org.jrevolorio.api.proyectoIntegrador.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, Integer> {

    Optional<Reservation> findById(int idReservation);

}
