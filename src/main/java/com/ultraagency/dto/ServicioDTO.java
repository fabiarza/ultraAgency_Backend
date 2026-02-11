package com.ultraagency.dto;

import com.ultraagency.models.Servicio;
import com.ultraagency.models.TipoServicio;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicioDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private TipoServicio tipoServicio;
    private List<String> caracteristicas;
    private BigDecimal precioDesde;
    private String icono;
    private Boolean destacado;

    public static ServicioDTO fromEntity(Servicio servicio) {
        return ServicioDTO.builder()
                .id(servicio.getId())
                .nombre(servicio.getNombre())
                .descripcion(servicio.getDescripcion())
                .tipoServicio(servicio.getTipoServicio())
                .caracteristicas(servicio.getCaracteristicas())
                .precioDesde(servicio.getPrecioDesde())
                .icono(servicio.getIcono())
                .destacado(servicio.getDestacado())
                .build();
    }
}
