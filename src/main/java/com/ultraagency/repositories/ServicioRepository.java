package com.ultraagency.repositories;

import com.ultraagency.models.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    List<Servicio> findByActivoTrueOrderByOrdenAsc();

    List<Servicio> findByActivoTrue();
}
