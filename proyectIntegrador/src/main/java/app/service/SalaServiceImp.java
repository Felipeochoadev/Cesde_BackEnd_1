package app.service;

import app.domain.Sala;
import app.service.inputports.SalaServiceInterface;
import app.service.ouputports.SalaRepositoryPort;
import java.util.List;

public class SalaServiceImp implements SalaServiceInterface {
    private final SalaRepositoryPort salaRepository;

    public SalaServiceImp(SalaRepositoryPort salaRepository) {
        this.salaRepository = salaRepository;
    }

    @Override
    public Sala create(String nombre, int capacidad, String tipo, double recargo) {
        Sala sala = new Sala(0, nombre, capacidad, tipo, recargo);
        return salaRepository.create(sala);
    }

    @Override
    public Sala getById(int id) {
        return salaRepository.getById(id);
    }

    @Override
    public List<Sala> getAll() {
        return salaRepository.getAll();
    }

    @Override
    public Sala update(int id, Sala sala) {
        return salaRepository.update(id, sala);
    }

    @Override
    public boolean delete(int id) {
        return salaRepository.delete(id);
    }
}
