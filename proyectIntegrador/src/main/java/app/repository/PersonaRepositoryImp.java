package app.repository;

import app.domain.Persona;
import app.service.ouputports.PersonaRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class PersonaRepositoryImp implements PersonaRepositoryPort {
    private List<Persona> dataSource = new ArrayList<>();
    private int currentId = 0;

    @Override
    public Persona create(Persona persona) {
        persona.setId(++currentId);
        dataSource.add(persona);
        return persona;
    }

    @Override
    public Persona getById(int id) {
        for (Persona p : dataSource) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Persona> getAll() {
        return new ArrayList<>(dataSource);
    }

    @Override
    public Persona update(int id, Persona persona) {
        for (int i = 0; i < dataSource.size(); i++) {
            if (dataSource.get(i).getId() == id) {
                persona.setId(id);
                dataSource.set(i, persona);
                return persona;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return dataSource.removeIf(p -> p.getId() == id);
    }
}
