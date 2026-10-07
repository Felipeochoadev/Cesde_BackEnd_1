package app.service.ouputports;

import app.domain.Reserva;
import java.util.List;

public interface ReservaRepositoryPort {
    public Reserva create(Reserva reserva);
    public Reserva getById(int id);
    public List<Reserva> getAll();
    public Reserva update(int id, Reserva reserva);
    public boolean delete(int id);
}
