package app.service.mappers;

import app.domain.Sala;
import app.service.dto.SalaResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class SalaDTOMapper {

    public static SalaResponseDTO toResponseDTO(Sala sala) {
        if (sala == null) return null;
        return new SalaResponseDTO(
                sala.getId(),
                sala.getNombre(),
                sala.getCapacidad(),
                sala.getTipo(),
                sala.getRecargo()
        );
    }

    public static List<SalaResponseDTO> toResponseDTOList(List<Sala> salas) {
        if (salas == null) return new ArrayList<>();
        List<SalaResponseDTO> list = new ArrayList<>();
        for (Sala s : salas) {
            list.add(toResponseDTO(s));
        }
        return list;
    }
}
