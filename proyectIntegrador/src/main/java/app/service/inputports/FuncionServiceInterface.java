package app.service.inputports;

import app.domain.Funcion;
import app.domain.Pelicula;
import app.domain.Sala;
import java.util.List;

public interface FuncionServiceInterface {
    public Funcion create(Pelicula pelicula, Sala sala, String fecha, String hora);
    public Funcion getById(int id);
    public List<Funcion> getAll();
    public Funcion update(int id, Funcion funcion);
    public boolean delete(int id);
}
