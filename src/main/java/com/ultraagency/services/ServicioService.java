package com.ultraagency.services;

import com.ultraagency.dto.ServicioDTO;
import com.ultraagency.models.Servicio;
import com.ultraagency.repositories.ServicioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    //Listar servicios activos (para frontend público)
    @Transactional(readOnly = true)
    public List<ServicioDTO> findAllActivos() {
        return servicioRepository.findByActivoTrueOrderByOrdenAsc()
                .stream()
                .map(ServicioDTO::fromEntity)
                .toList();
    }

    //Listar todos (para admin)
    @Transactional(readOnly = true)
    public List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    //Buscar por ID
    @Transactional(readOnly = true)
    public Servicio findById(Long id) {
        return servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
    }

    //Crear servicio
    public Servicio create(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    //Actualizar servicio
    public Servicio update(Long id, Servicio incoming) {
        Servicio existing = findById(id);

        existing.setNombre(incoming.getNombre());
        existing.setDescripcion(incoming.getDescripcion());
        existing.setTipoServicio(incoming.getTipoServicio());
        existing.setCaracteristicas(incoming.getCaracteristicas());
        existing.setPrecioDesde(incoming.getPrecioDesde());
        existing.setIcono(incoming.getIcono());
        existing.setDestacado(incoming.getDestacado());
        existing.setActivo(incoming.getActivo());
        existing.setOrden(incoming.getOrden());

        return servicioRepository.save(existing);
    }

    //Eliminar servicio
    public void delete(Long id) {
        servicioRepository.deleteById(id);
    }
}
