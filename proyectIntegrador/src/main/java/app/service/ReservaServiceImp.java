package app.service;

import app.domain.Cliente;
import app.domain.Funcion;
import app.domain.Reserva;
import app.service.inputports.ReservaServiceInterface;
import app.service.ouputports.ReservaRepositoryPort;
import java.util.List;

public class ReservaServiceImp implements ReservaServiceInterface {
    private final ReservaRepositoryPort reservaRepository;

    public ReservaServiceImp(ReservaRepositoryPort reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public Reserva create(Cliente cliente, Funcion funcion, int cantidadBoletos) {
        Reserva reserva = new Reserva(0, cliente, funcion, cantidadBoletos);
        return reservaRepository.create(reserva);
    }

    @Override
    public Reserva getById(int id) {
        return reservaRepository.getById(id);
    }

    @Override
    public List<Reserva> getAll() {
        return reservaRepository.getAll();
    }

    @Override
    public Reserva update(int id, Reserva reserva) {
        return reservaRepository.update(id, reserva);
    }

    @Override
    public boolean delete(int id) {
        return reservaRepository.delete(id);
    }
}
