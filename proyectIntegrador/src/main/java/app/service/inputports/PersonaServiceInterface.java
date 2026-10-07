package app.service.inputports;

import app.domain.Persona;
import java.util.List;

public interface PersonaServiceInterface {
    public Persona create(Persona persona);
    public Persona getById(int id);
    public List<Persona> getAll();
    public Persona update(int id, Persona persona);
    public boolean delete(int id);
}
