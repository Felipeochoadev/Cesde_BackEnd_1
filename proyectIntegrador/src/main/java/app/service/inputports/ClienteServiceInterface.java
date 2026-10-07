package app.service.inputports;

import app.domain.Cliente;
import java.util.List;

public interface ClienteServiceInterface {
    public Cliente create(String nombre, String correo, String telefono, int edad);
    public Cliente getById(int id);
    public List<Cliente> getAll();
    public Cliente update(int id, Cliente cliente);
    public boolean delete(int id);
}
