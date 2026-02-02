package com.ultraagency.services;

import com.ultraagency.models.Event;
import com.ultraagency.models.Registration;
import com.ultraagency.models.RegistrationStatus;
import com.ultraagency.repositories.EventRepository;
import com.ultraagency.repositories.RegistrationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;

    public RegistrationService(RegistrationRepository registrationRepository, EventRepository eventRepository){
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
    }

    public Registration register(Long eventId,Registration registration){
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        long current = registrationRepository.countByEventId(eventId);
        if(current >= event.getCapacity()) {
            throw  new RuntimeException("No hay plazas disponibles");
        }
        boolean exists = registrationRepository.existsByEventIdAndEmail(eventId,registration.getEmail());
        if(exists){
            throw  new RuntimeException("Email ya inscrito en este evento");
        }
        registration.setEvent(event);
        registration.setStatus(RegistrationStatus.PENDING);

        return  registrationRepository.save(registration);

    }
    public List<Registration> findByEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        return registrationRepository.findByEvent(event);

    }
}
