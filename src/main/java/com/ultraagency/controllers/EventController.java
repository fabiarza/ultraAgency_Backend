package com.ultraagency.controllers;

import com.ultraagency.models.Event;
import com.ultraagency.services.EventService;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    //Obtener todos los eventos
    @GetMapping
    public List<Event> findAll(){
        return eventService.findAll();
    }
    //Un solo Evento por Id
    @GetMapping("/{id}")
    public ResponseEntity<Event> findById(@PathVariable Long id){
        return eventService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //Crear Evento
    @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event event){
        Event saved = eventService.create(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);

    }
    //Actualizar 1 Evento p o Id
    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable Long id,@RequestBody Event event){
        Event updated = eventService.update(id,event);
        return ResponseEntity.ok(updated);
    }
    //Borrar Evento por Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
