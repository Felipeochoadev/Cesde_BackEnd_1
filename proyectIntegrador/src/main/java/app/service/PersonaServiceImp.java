package app.service;

import app.domain.Persona;
import app.service.inputports.PersonaServiceInterface;
import app.service.ouputports.PersonaRepositoryPort;
import java.util.List;

public class PersonaServiceImp implements PersonaServiceInterface {
    private final PersonaRepositoryPort personaRepository;

    public PersonaServiceImp(PersonaRepositoryPort personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona create(int id, String nombre, String correo, String telefono) {
        Persona persona = new Persona(id, nombre, correo, telefono);
        return personaRepository.create(persona);
    }

    @Override
    public Persona getById(int id) {
        return personaRepository.getById(id);
    }

    @Override
    public List<Persona> getAll() {
        return personaRepository.getAll();
    }

    @Override
    public Persona update(int id, Persona persona) {
        return personaRepository.update(id, persona);
    }

    @Override
    public boolean delete(int id) {
        return personaRepository.delete(id);
    }
}
