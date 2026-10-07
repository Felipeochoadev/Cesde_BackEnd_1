package app.service;

import app.domain.Factura;
import app.service.inputports.FacturaServiceInterface;
import app.service.ouputports.FacturaRepositoryPort;
import java.util.List;

public class FacturaServiceImp implements FacturaServiceInterface {
    private final FacturaRepositoryPort facturaRepository;

    public FacturaServiceImp(FacturaRepositoryPort facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public Factura create(Factura factura) {
        return facturaRepository.create(factura);
    }

    @Override
    public Factura getById(int id) {
        return facturaRepository.getById(id);
    }

    @Override
    public List<Factura> getAll() {
        return facturaRepository.getAll();
    }

    @Override
    public Factura update(int id, Factura factura) {
        return facturaRepository.update(id, factura);
    }

    @Override
    public boolean delete(int id) {
        return facturaRepository.delete(id);
    }
}
