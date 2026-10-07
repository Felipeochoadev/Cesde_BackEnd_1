package app.service;

import app.domain.Funcion;
import app.service.inputports.FuncionServiceInterface;
import app.service.ouputports.FuncionRepositoryPort;
import java.util.List;

public class FuncionServiceImp implements FuncionServiceInterface {
    private final FuncionRepositoryPort funcionRepository;

    public FuncionServiceImp(FuncionRepositoryPort funcionRepository) {
        this.funcionRepository = funcionRepository;
    }

    @Override
    public Funcion create(Funcion funcion) {
        return funcionRepository.create(funcion);
    }

    @Override
    public Funcion getById(int id) {
        return funcionRepository.getById(id);
    }

    @Override
    public List<Funcion> getAll() {
        return funcionRepository.getAll();
    }

    @Override
    public Funcion update(int id, Funcion funcion) {
        return funcionRepository.update(id, funcion);
    }

    @Override
    public boolean delete(int id) {
        return funcionRepository.delete(id);
    }
}
