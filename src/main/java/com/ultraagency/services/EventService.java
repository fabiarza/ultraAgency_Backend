package com.ultraagency.services;

import com.ultraagency.models.Event;
import com.ultraagency.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    //Crear un Evento
    public Event create(Event event){
        return eventRepository.save(event);
    }

    //Listar todos los Eventos
    public List<Event> findAll(){
        return eventRepository.findAll();
    }
    //Buscar Evento por id
    public Optional<Event> findById(Long id){
        return  eventRepository.findById(id);
    }

    public Event update(Long id, Event incoming) {
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        existing.setTitle(incoming.getTitle());
        existing.setDescription(incoming.getDescription());
        existing.setEventType(incoming.getEventType());
        existing.setCapacity(incoming.getCapacity());
        existing.setPrice(incoming.getPrice());
        existing.setLocation(incoming.getLocation());
        existing.setStartDateTime(incoming.getStartDateTime());
        existing.setEndDateTime(incoming.getEndDateTime());

        return  eventRepository.save(existing);
    }

    public void delete(Long id) {

        eventRepository.deleteById(id);
    }
}
