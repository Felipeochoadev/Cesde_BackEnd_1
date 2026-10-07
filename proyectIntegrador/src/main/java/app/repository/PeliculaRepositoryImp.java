package app.repository;

import app.domain.Pelicula;
import app.service.ouputports.PeliculaRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class PeliculaRepositoryImp implements PeliculaRepositoryPort {
    private List<Pelicula> baseDeDatos = new ArrayList<>();

    @Override
    public void guardar(Pelicula pelicula) {
        baseDeDatos.add(pelicula);
    }

    @Override
    public List<Pelicula> obtenerTodas() {
        return baseDeDatos;
    }

    @Override
    public Pelicula buscarPorId(int id) {
        for (Pelicula p : baseDeDatos) {
            if (p.getId() == id) return p;
        }
        return null;
    }
}
