package app.repository;

import app.domain.Pelicula;
import app.service.ouputports.PeliculaRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class PeliculaRepositoryImp implements PeliculaRepositoryPort {
    private List<Pelicula> dataSource = new ArrayList<>();
    private int currentId = 0;

    @Override
    public Pelicula create(Pelicula pelicula) {
        pelicula.setId(++currentId);
        dataSource.add(pelicula);
        return pelicula;
    }

    @Override
    public Pelicula getById(int id) {
        for (Pelicula p : dataSource) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Pelicula> getAll() {
        return new ArrayList<>(dataSource);
    }

    @Override
    public Pelicula update(int id, Pelicula pelicula) {
        for (int i = 0; i < dataSource.size(); i++) {
            if (dataSource.get(i).getId() == id) {
                pelicula.setId(id);
                dataSource.set(i, pelicula);
                return pelicula;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return dataSource.removeIf(p -> p.getId() == id);
    }
}
