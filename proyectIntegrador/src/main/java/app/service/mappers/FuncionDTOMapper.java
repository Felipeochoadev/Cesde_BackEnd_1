package app.service.mappers;

import app.domain.Funcion;
import app.service.dto.FuncionResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class FuncionDTOMapper {

    public static FuncionResponseDTO toResponseDTO(Funcion funcion) {
        if (funcion == null) return null;
        String peliculaTitulo = (funcion.getPelicula() != null) ? funcion.getPelicula().getTitulo() : "";
        String salaNombre = (funcion.getSala() != null) ? funcion.getSala().getNombre() : "";
        return new FuncionResponseDTO(
                funcion.getId(),
                peliculaTitulo,
                salaNombre,
                funcion.getFecha(),
                funcion.getHora(),
                funcion.getPrecioBoleto()
        );
    }

    public static List<FuncionResponseDTO> toResponseDTOList(List<Funcion> funciones) {
        if (funciones == null) return new ArrayList<>();
        List<FuncionResponseDTO> list = new ArrayList<>();
        for (Funcion f : funciones) {
            list.add(toResponseDTO(f));
        }
        return list;
    }
}
