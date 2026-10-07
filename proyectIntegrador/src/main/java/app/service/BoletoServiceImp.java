package app.service;

import app.domain.Boleto;
import app.domain.Funcion;
import app.service.inputports.BoletoServiceInterface;
import app.service.ouputports.BoletoRepositoryPort;
import java.util.List;

public class BoletoServiceImp implements BoletoServiceInterface {
    private final BoletoRepositoryPort boletoRepository;

    public BoletoServiceImp(BoletoRepositoryPort boletoRepository) {
        this.boletoRepository = boletoRepository;
    }

    @Override
    public Boleto create(String codigo, Funcion funcion, String asiento) {
        Boleto boleto = new Boleto(0, codigo, funcion, asiento);
        return boletoRepository.create(boleto);
    }

    @Override
    public Boleto getById(int id) {
        return boletoRepository.getById(id);
    }

    @Override
    public List<Boleto> getAll() {
        return boletoRepository.getAll();
    }

    @Override
    public Boleto update(int id, Boleto boleto) {
        return boletoRepository.update(id, boleto);
    }

    @Override
    public boolean delete(int id) {
        return boletoRepository.delete(id);
    }
}
