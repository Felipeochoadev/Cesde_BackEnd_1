package app.service.inputports;

import app.domain.Sala;
import java.util.List;

public interface SalaServiceInterface {
    public Sala create(String nombre, int capacidad, String tipo, double recargo);
    public Sala getById(int id);
    public List<Sala> getAll();
    public Sala update(int id, Sala sala);
    public boolean delete(int id);
}
