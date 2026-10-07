package app.service.inputports;

import app.domain.Pelicula;
import java.util.List;

public interface PeliculaServiceInterface {
    public Pelicula create(Pelicula pelicula);
    public Pelicula getById(int id);
    public List<Pelicula> getAll();
    public Pelicula update(int id, Pelicula pelicula);
    public boolean delete(int id);
}
