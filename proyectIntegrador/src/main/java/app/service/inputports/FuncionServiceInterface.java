package app.service.inputports;

import app.domain.Funcion;
import java.util.List;

public interface FuncionServiceInterface {
    public Funcion create(Funcion funcion);
    public Funcion getById(int id);
    public List<Funcion> getAll();
    public Funcion update(int id, Funcion funcion);
    public boolean delete(int id);
}
