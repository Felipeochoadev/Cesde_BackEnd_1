package app.service.ouputports;

import app.domain.Sala;
import java.util.List;

public interface SalaRepositoryPort {
    public Sala create(Sala sala);
    public Sala getById(int id);
    public List<Sala> getAll();
    public Sala update(int id, Sala sala);
    public boolean delete(int id);
}
