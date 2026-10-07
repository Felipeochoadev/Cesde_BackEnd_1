package app.service.inputports;

import app.domain.Cliente;
import java.util.List;

public interface ClienteServiceInterface {
    public Cliente create(Cliente cliente);
    public Cliente getById(int id);
    public List<Cliente> getAll();
    public Cliente update(int id, Cliente cliente);
    public boolean delete(int id);
}
