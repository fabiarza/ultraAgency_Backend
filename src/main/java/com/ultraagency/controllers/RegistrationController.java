package com.ultraagency.controllers;

import com.ultraagency.models.Registration;
import com.ultraagency.services.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events/{eventId}/registrations")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
    //Crear Registro/Inscripcion
    @PostMapping
    public ResponseEntity<Registration> register(@PathVariable Long eventId, @RequestBody Registration registration){

            Registration saved = registrationService.register(eventId,registration);
            return  ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(saved);
    }
    //Listar Inscripciones por cada Evento
    @GetMapping
    public List<Registration> list(@PathVariable Long eventId){

        return registrationService.findByEvent(eventId);
    }
}
