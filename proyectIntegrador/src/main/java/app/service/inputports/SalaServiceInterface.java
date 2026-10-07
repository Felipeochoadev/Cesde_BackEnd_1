package app.service.inputports;

import app.domain.Sala;
import java.util.List;

public interface SalaServiceInterface {
    public Sala create(Sala sala);
    public Sala getById(int id);
    public List<Sala> getAll();
    public Sala update(int id, Sala sala);
    public boolean delete(int id);
}
