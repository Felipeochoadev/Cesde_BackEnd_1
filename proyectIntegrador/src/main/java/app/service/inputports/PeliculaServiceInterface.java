package app.service.inputports;

import app.domain.Pelicula;
import java.util.List;

public interface PeliculaServiceInterface {
    Pelicula buscarPorId(int id);

    Pelicula createPelicula(int id, String titulo, String genero, int duracion, double precio, int edadMin);

    List<Pelicula> listarPeliculas();
}
