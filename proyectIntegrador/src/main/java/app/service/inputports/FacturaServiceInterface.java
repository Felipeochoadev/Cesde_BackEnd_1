package app.service.inputports;

import app.domain.Factura;
import app.domain.Reserva;
import java.util.List;

public interface FacturaServiceInterface {
    public Factura create(int id, String numero, Reserva reserva, String metodoPago);
    public Factura getById(int id);
    public List<Factura> getAll();
    public Factura update(int id, Factura factura);
    public boolean delete(int id);
}
