package app.service;

import app.domain.Pelicula;
import app.service.inputports.PeliculaServiceInterface;
import app.service.ouputports.PeliculaRepositoryPort;
import java.util.List;

public class PeliculaServiceImp implements PeliculaServiceInterface {
    private PeliculaRepositoryPort repository;

    public PeliculaServiceImp(PeliculaRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Pelicula buscarPorId(int id) {
        return null;
    }

    @Override
    public Pelicula createPelicula(int id, String titulo, String genero, int duracion, double precio, int edadMin) {
        return null;
    }

    @Override
    public List<Pelicula> listarPeliculas() {
        return List.of();
    }
}
