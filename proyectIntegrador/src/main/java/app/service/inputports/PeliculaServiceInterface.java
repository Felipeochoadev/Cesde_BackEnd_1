package app.service.inputports;

import app.domain.Pelicula;
import java.util.List;

public interface PeliculaServiceInterface {
    public Pelicula create(int id, String titulo, String genero, int duracionMinutos, double precioBase, int edadMinima);
    public Pelicula getById(int id);
    public List<Pelicula> getAll();
    public Pelicula update(int id, Pelicula pelicula);
    public boolean delete(int id);
}
