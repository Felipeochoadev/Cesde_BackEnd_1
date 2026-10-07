package app.service.ouputports;

import app.domain.Factura;
import java.util.List;

public interface FacturaRepositoryPort {
    public Factura create(Factura factura);
    public Factura getById(int id);
    public List<Factura> getAll();
    public Factura update(int id, Factura factura);
    public boolean delete(int id);
}
