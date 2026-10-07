package app.service;

import app.domain.Pelicula;
import app.service.inputports.PeliculaServiceInterface;
import app.service.ouputports.PeliculaRepositoryPort;
import java.util.List;

public class PeliculaServiceImp implements PeliculaServiceInterface {
    private final PeliculaRepositoryPort peliculaRepository;

    public PeliculaServiceImp(PeliculaRepositoryPort peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    @Override
    public Pelicula create(String titulo, String genero, int duracionMinutos, double precioBase, int edadMinima) {
        Pelicula pelicula = new Pelicula(0, titulo, genero, duracionMinutos, precioBase, edadMinima);
        return peliculaRepository.create(pelicula);
    }

    @Override
    public Pelicula getById(int id) {
        return peliculaRepository.getById(id);
    }

    @Override
    public List<Pelicula> getAll() {
        return peliculaRepository.getAll();
    }

    @Override
    public Pelicula update(int id, Pelicula pelicula) {
        return peliculaRepository.update(id, pelicula);
    }

    @Override
    public boolean delete(int id) {
        return peliculaRepository.delete(id);
    }
}
