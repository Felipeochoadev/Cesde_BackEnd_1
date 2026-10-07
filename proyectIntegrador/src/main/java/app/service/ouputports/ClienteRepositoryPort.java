package app.service.ouputports;

import app.domain.Cliente;
import java.util.List;

public interface ClienteRepositoryPort {
    public Cliente create(Cliente cliente);
    public Cliente getById(int id);
    public List<Cliente> getAll();
    public Cliente update(int id, Cliente cliente);
    public boolean delete(int id);
}
