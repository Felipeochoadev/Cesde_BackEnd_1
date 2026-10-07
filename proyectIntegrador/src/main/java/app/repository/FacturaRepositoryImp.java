package app.repository;

import app.domain.Factura;
import app.service.ouputports.FacturaRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class FacturaRepositoryImp implements FacturaRepositoryPort {
    private List<Factura> dataSource = new ArrayList<>();

    @Override
    public Factura create(Factura factura) {
        dataSource.add(factura);
        return factura;
    }

    @Override
    public Factura getById(int id) {
        for (Factura f : dataSource) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    @Override
    public List<Factura> getAll() {
        return new ArrayList<>(dataSource);
    }

    @Override
    public Factura update(int id, Factura factura) {
        for (int i = 0; i < dataSource.size(); i++) {
            if (dataSource.get(i).getId() == id) {
                dataSource.set(i, factura);
                return factura;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return dataSource.removeIf(f -> f.getId() == id);
    }
}
