package app.service.ouputports;

import app.domain.Pelicula;
import java.util.List;

public interface PeliculaRepositoryPort {
    void guardar(Pelicula pelicula);
    List<Pelicula> obtenerTodas();
    Pelicula buscarPorId(int id);
}
