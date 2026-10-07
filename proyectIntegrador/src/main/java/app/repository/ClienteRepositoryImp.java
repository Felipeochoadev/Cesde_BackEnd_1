package app.repository;

import app.domain.Cliente;
import app.service.ouputports.ClienteRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImp implements ClienteRepositoryPort {
    private List<Cliente> dataSource = new ArrayList<>();

    @Override
    public Cliente create(Cliente cliente) {
        dataSource.add(cliente);
        return cliente;
    }

    @Override
    public Cliente getById(int id) {
        for (Cliente c : dataSource) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Cliente> getAll() {
        return new ArrayList<>(dataSource);
    }

    @Override
    public Cliente update(int id, Cliente cliente) {
        for (int i = 0; i < dataSource.size(); i++) {
            if (dataSource.get(i).getId() == id) {
                dataSource.set(i, cliente);
                return cliente;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return dataSource.removeIf(c -> c.getId() == id);
    }
}
