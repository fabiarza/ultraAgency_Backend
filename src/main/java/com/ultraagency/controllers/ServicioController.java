package com.ultraagency.controllers;

import com.ultraagency.dto.ServicioDTO;
import com.ultraagency.models.Servicio;
import com.ultraagency.services.ServicioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    //GET público - servicios activos para el frontend
    @GetMapping
    public List<ServicioDTO> listarActivos() {
        return servicioService.findAllActivos();
    }

    //GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> findById(@PathVariable Long id) {
        Servicio servicio = servicioService.findById(id);
        return ResponseEntity.ok(servicio);
    }

    //GET admin - todos los servicios
    @GetMapping("/admin")
    public List<Servicio> listarTodos() {
        return servicioService.findAll();
    }

    //POST - crear servicio
    @PostMapping
    public ResponseEntity<Servicio> crear(@RequestBody Servicio servicio) {
        Servicio saved = servicioService.create(servicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //PUT - actualizar servicio
    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizar(@PathVariable Long id, @RequestBody Servicio servicio) {
        Servicio updated = servicioService.update(id, servicio);
        return ResponseEntity.ok(updated);
    }

    //DELETE - eliminar servicio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
