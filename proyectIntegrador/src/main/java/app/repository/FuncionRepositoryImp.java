package app.repository;

import app.domain.Funcion;
import app.service.ouputports.FuncionRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class FuncionRepositoryImp implements FuncionRepositoryPort {
    private List<Funcion> dataSource = new ArrayList<>();

    @Override
    public Funcion create(Funcion funcion) {
        dataSource.add(funcion);
        return funcion;
    }

    @Override
    public Funcion getById(int id) {
        for (Funcion f : dataSource) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    @Override
    public List<Funcion> getAll() {
        return new ArrayList<>(dataSource);
    }

    @Override
    public Funcion update(int id, Funcion funcion) {
        for (int i = 0; i < dataSource.size(); i++) {
            if (dataSource.get(i).getId() == id) {
                dataSource.set(i, funcion);
                return funcion;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return dataSource.removeIf(f -> f.getId() == id);
    }
}
