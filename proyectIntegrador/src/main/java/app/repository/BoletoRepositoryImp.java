package app.repository;

import app.domain.Boleto;
import app.service.ouputports.BoletoRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class BoletoRepositoryImp implements BoletoRepositoryPort {
    private List<Boleto> dataSource = new ArrayList<>();
    private int currentId = 0;

    @Override
    public Boleto create(Boleto boleto) {
        boleto.setId(++currentId);
        dataSource.add(boleto);
        return boleto;
    }

    @Override
    public Boleto getById(int id) {
        for (Boleto b : dataSource) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    @Override
    public List<Boleto> getAll() {
        return new ArrayList<>(dataSource);
    }

    @Override
    public Boleto update(int id, Boleto boleto) {
        for (int i = 0; i < dataSource.size(); i++) {
            if (dataSource.get(i).getId() == id) {
                boleto.setId(id);
                dataSource.set(i, boleto);
                return boleto;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return dataSource.removeIf(b -> b.getId() == id);
    }
}
