package com.ultraagency.repositories;

import com.ultraagency.models.Event;
import com.ultraagency.models.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByEvent(Event event);

     //metodo para obtener todas las inscripciones de un evento
    List<Registration> findByEventId(Long eventId);

   //metodo para comprobar si esta persona ya esta inscripta en este evento
    boolean existsByEventIdAndEmail(Long eventId, String email);

    //metodo para saber cuantas inscripciones tiene el evento
    //es para controlar capacidad
    long countByEventId(Long eventId);


}
