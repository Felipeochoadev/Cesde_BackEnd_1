package app.service.mappers;

import app.domain.Pelicula;
import app.service.dto.PeliculaResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class PeliculaDTOMapper {

    public static PeliculaResponseDTO toResponseDTO(Pelicula pelicula) {
        if (pelicula == null) return null;
        return new PeliculaResponseDTO(
                pelicula.getId(),
                pelicula.getTitulo(),
                pelicula.getGenero(),
                pelicula.getDuracionMinutos(),
                pelicula.getPrecioBase(),
                pelicula.getEdadMinima()
        );
    }

    public static List<PeliculaResponseDTO> toResponseDTOList(List<Pelicula> peliculas) {
        if (peliculas == null) return new ArrayList<>();
        List<PeliculaResponseDTO> list = new ArrayList<>();
        for (Pelicula p : peliculas) {
            list.add(toResponseDTO(p));
        }
        return list;
    }
}
