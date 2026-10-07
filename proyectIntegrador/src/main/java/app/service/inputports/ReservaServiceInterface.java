package app.service.inputports;

import app.domain.Cliente;
import app.domain.Funcion;
import app.domain.Reserva;
import java.util.List;

public interface ReservaServiceInterface {
    public Reserva create(Cliente cliente, Funcion funcion, int cantidadBoletos);
    public Reserva getById(int id);
    public List<Reserva> getAll();
    public Reserva update(int id, Reserva reserva);
    public boolean delete(int id);
}
